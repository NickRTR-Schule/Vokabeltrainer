package benutzerschnittstelle;

import benutzerschnittstelle.komponenten.CustomButton;
import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.komponenten.CustomTextField;
import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import exceptions.EndOfAbfrageException;
import fachkonzept.datamangement.converting.CustomConverter;
import steuerung.AbfrageSteuerung;
import steuerung.MainFrameSteuerung;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

/**
 * The Screen to test the Users knowledge and quiz the vocabs stored
 */
public final class Abfrage extends CustomPanel
{
    /**
     * The Controller to this View
     */
    private final AbfrageSteuerung steuerung;
    private final JLabel wortLabel;
    private final JLabel verwendungshinweisLabel;
    private final JLabel lautschriftLabel;
    private final CustomTextField uebersetzungField;
    private final JLabel abbildungsLabel;
    private final JButton audioButton;
    private Clip audioClip;
    private boolean audioplaying;
    private int enteredNumberVoks;
    private int anzahlAbfragen = 0;
    private int anzahlRichtig = 0;

    public Abfrage()
    {
        super("Abfrage");
        enteredNumberVoks = frageVokabelAnzahl();
        Kategorie kat = frageKategorie();
        steuerung = new AbfrageSteuerung(enteredNumberVoks, kat);
        wortLabel = new JLabel("Wort", SwingConstants.CENTER);
        verwendungshinweisLabel = new JLabel("Verwendungshinweis", SwingConstants.CENTER);
        lautschriftLabel = new JLabel("Lautschrift", SwingConstants.CENTER);
        uebersetzungField = new CustomTextField();
        uebersetzungField.setHorizontalAlignment(JTextField.CENTER);
        abbildungsLabel = new JLabel();
        audioButton = new JButton();
        audioplaying = false;
        audioClip = null;
        setValues();
        build();
    }

    private void setValues()
    {
        setName(getTitle());
        setBackground(Color.WHITE);
    }

    private void build()
    {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(15, 0, 15, 0);
        layout.setConstraints(this, constraints);
        constraints.weightx = .5;
        setLayout(layout);
        constraints.gridx = 0;
        final JPanel spacerPanel1 = new JPanel();
        spacerPanel1.setBackground(Color.WHITE);
        add(spacerPanel1, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(abbildungsLabel);
        constraints.gridy = 1;
        add(wortLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(lautschriftLabel, constraints);
        constraints.gridx = 2;
        audioButton.addActionListener((ignored) -> {
            updateAudioLabel();
            if (audioplaying)
            {
                audioClip.stop();
            } else
            {
                audioClip.start();
            }
            audioplaying = !audioplaying;
        });
        if (steuerung.liesAktuelleVokabel().liesAussprache() != null)
        {
            //add(audioButton, constraints);
        }
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(verwendungshinweisLabel, constraints);
        constraints.gridy = 4;
        add(uebersetzungField, constraints);
        final CustomButton checkBtn = new CustomButton("Check");
        checkBtn.addActionListener((e) -> checkVok());
        constraints.gridy = 5;
        add(checkBtn, constraints);
        constraints.gridx = 3;
        final JPanel spacerPanel2 = new JPanel();
        spacerPanel2.setBackground(Color.WHITE);
        add(spacerPanel2, constraints);
        uebersetzungField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    checkVok();
                }
            }
        });
        if (steuerung.anzahlVoks() < enteredNumberVoks)
        {
            JOptionPane.showMessageDialog(this, "Nicht ausreichend Vokabeln gespeichert");
            enteredNumberVoks = steuerung.anzahlVoks();
        }
        wortLabel.setText(steuerung.liesAktuelleVokabel().liesWort());
        updateAbbildungsLabel(steuerung.liesAktuelleVokabel().liesAbbildung());
        updateAudio(steuerung.liesAktuelleVokabel().liesAussprache());
        wortLabel.setFont(new Font(MainFrame.liesFont().getFontName(), Font.BOLD, 16));
        lautschriftLabel.setText(steuerung.liesAktuelleVokabel().liesLautschrift());
        verwendungshinweisLabel.setText(steuerung.liesAktuelleVokabel().liesVerwendungshinweis());
    }

    private void updateAbbildungsLabel(byte[] abbildung)
    {
        if (abbildung == null)
        {
            final InputStream stream = Abfrage.class.getClassLoader()
                    .getResourceAsStream("Image-placeholder.png");
            try
            {
                assert stream != null;
                abbildungsLabel.setIcon(CustomConverter.byteToIcon(stream.readAllBytes(), 150));
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            return;
        }
        try
        {
            abbildungsLabel.setIcon(CustomConverter.byteToIcon(abbildung, 150));
        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Fehler beim Laden der Abbildung");
        }
    }

    private void updateAudioLabel()
    {
        try
        {
            if (audioplaying)
            {
                audioButton.setIcon(loadImage(PlayType.pause));
            } else
            {
                audioButton.setIcon(loadImage(PlayType.play));
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "");
        }
    }

    private void updateAudio(byte[] audio)
    {
        if (audio == null)
        {
            return;
        }
        try
        {
            audioClip = CustomConverter.byteToAudio(audio);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Fehler beim Laden des Audios");
        }
    }

    private int frageVokabelAnzahl()
    {
        final String input = JOptionPane.showInputDialog(
                "Wie viele Vokabeln sollen abgefragt werden?", 30);
        if (input == null || input.equals(""))
        {
            steuerung.oeffnenAbgebrochen();
            return 0;
        } else
        {
            return Integer.parseInt(input);
        }
    }

    private Kategorie frageKategorie()
    {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        final JComboBox<Kategorie> dropdown = new JComboBox<>(Datenbank.liesKategorien().toArray(new Kategorie[0]));
        dropdown.addItem(new Kategorie("Alle", new Date(System.currentTimeMillis()), Datenbank.liesVokabeln()));
        panel.add(dropdown);
        if (JOptionPane.showConfirmDialog(this, panel) == JOptionPane.OK_OPTION)
        {
            return (Kategorie) dropdown.getSelectedItem();
        } else
        {
            return null;
        }
    }

    private void frageAb()
    {
        try
        {
            wortLabel.setText(steuerung.naechsteVokabel().liesWort());
            verwendungshinweisLabel.setText(steuerung.liesAktuelleVokabel().liesVerwendungshinweis());
            updateAbbildungsLabel(steuerung.liesAktuelleVokabel().liesAbbildung());
            lautschriftLabel.setText(steuerung.liesAktuelleVokabel().liesLautschrift());
            updateAudio(steuerung.liesAktuelleVokabel().liesAussprache());
        } catch (EndOfAbfrageException ignored)
        {
            double erfolgsquote = (double) anzahlRichtig / anzahlAbfragen * 100;
            erfolgsquote = (double) Math.round(erfolgsquote * 100) / 100;
            JOptionPane.showMessageDialog(this, "Anzahl Vokabeln gelernt: " + enteredNumberVoks + "\nAnzahl Abfragen: "
                    + anzahlAbfragen + "\nDavon richtig: " + anzahlRichtig + "\nErfolgsquote: " + erfolgsquote + "%");
            MainFrameSteuerung.getInstance().openDashboard();
        }
    }

    private void checkVok()
    {
        anzahlAbfragen++;
        try
        {
            boolean richtig = steuerung.pruefeEingabe(uebersetzungField.getText());
            if (richtig)
            {
                anzahlRichtig++;
                vokRichtig();
                uebersetzungField.setText("");
            } else
            {
                vokFalsch();
                uebersetzungField.selectAll();
            }
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void vokFalsch()
    {
        JOptionPane.showMessageDialog(this, "Deine Eingabe ist leider falsch. Versuche es noch einmal");
    }

    public void vokRichtig()
    {
        frageAb();
    }

    private ImageIcon loadImage(PlayType type)
    {
        final String name = switch (type)
        {
            case play -> "play.png";
            case pause -> "pause.png";
        };
        final InputStream stream = Abfrage.class.getClassLoader()
                .getResourceAsStream(name);
        try
        {
            assert stream != null;
            return CustomConverter.byteToIcon(stream.readAllBytes(), 50);
        } catch (Exception ignored)
        {
            JOptionPane.showMessageDialog(this, "Fehler beim Laden der Datei");
        }
        return null;
    }

    private enum PlayType
    {
        play,
        pause
    }
}
