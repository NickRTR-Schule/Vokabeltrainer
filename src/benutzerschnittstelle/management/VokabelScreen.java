package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.komponenten.CustomTextField;
import benutzerschnittstelle.navigation.NavigationBar;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.listeners.CustomKeyListener;
import fachkonzept.navigation.NavigationStack;
import steuerung.management.VokabelScreenSteuerung;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Screen to add / create a new Vocabulary.
 */
public final class VokabelScreen extends CustomPanel
{

    /**
     * The Controller to this View
     */
    private final VokabelScreenSteuerung steuerung;

    // Text Fields
    private final CustomTextField wortTxtField;
    private final CustomTextField uebersetzungTxtField;
    private final CustomTextField lautschriftTxtField;
    private final CustomTextField verwendungsHinweisTxtField;
    private final JLabel abbildungsLabel;
    private byte[] abbildung;
    private ArrayList<Kategorie> kategorien;
    private Vokabel vok;
    private boolean bearbeiten;

    public VokabelScreen()
    {
        super("Vokabel");
        bearbeiten = false;
        steuerung = new VokabelScreenSteuerung();
        wortTxtField = new CustomTextField();
        uebersetzungTxtField = new CustomTextField();
        lautschriftTxtField = new CustomTextField();
        verwendungsHinweisTxtField = new CustomTextField();
        kategorien = new ArrayList<>();
        vok = null;
        abbildung = null;
        abbildungsLabel = new JLabel(iconLaden());
        setValues();
    }

    public VokabelScreen(Vokabel vokabel)
    {
        this();
        vok = vokabel;
        bearbeiten = true;
        wortTxtField.setText(vokabel.liesWort());
        uebersetzungTxtField.setText(vokabel.liesUebersetzung());
        lautschriftTxtField.setText(vokabel.liesLautschrift());
        verwendungsHinweisTxtField.setText(vokabel.liesVerwendungshinweis());
        kategorien = vokabel.liesKategorien();
        abbildung = vokabel.liesAbbildung();
        updateImage();
    }

    private ImageIcon iconLaden()
    {
        try (final InputStream stream = NavigationBar.class.getClassLoader()
                .getResourceAsStream("Image-placeholder.png"))
        {
            final ImageIcon icon;
            try
            {
                assert stream != null;
                icon = new ImageIcon(stream.readAllBytes());
                final Image image = icon.getImage();
                final Image scaledInstance = image.getScaledInstance(75, 75,
                        Image.SCALE_DEFAULT);
                icon.setImage(scaledInstance);
                return icon;
            } catch (IOException e)
            {
                e.printStackTrace();
                return new ImageIcon();
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void setValues()
    {
        final JScrollPane pane = new JScrollPane();
        pane.setBackground(Color.WHITE);
        pane.setLayout(new ScrollPaneLayout());
        pane.setViewportView(build());
        add(pane);
        setName(getName());
    }

    private JPanel build()
    {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        final GridBagLayout layout = new GridBagLayout();
        layout.setConstraints(this, constraints);
        final JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(new JLabel("Wort"), constraints);
        wortTxtField.addKeyListener(new CustomKeyListener());
        constraints.gridy = 1;
        panel.add(wortTxtField, constraints);
        constraints.gridy = 2;
        panel.add(new JLabel("Uebersetzung"), constraints);
        uebersetzungTxtField.addKeyListener(new CustomKeyListener());
        constraints.gridy = 1;
        constraints.gridx = 2;
        constraints.gridheight = 4;
        panel.add(abbildungsLabel, constraints);
        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridheight = 1;
        panel.add(uebersetzungTxtField, constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 5;
        constraints.gridx = 1;
        final JButton abbildungsBtn = new JButton("Abbildung hinzufuegen");
        abbildungsBtn.addActionListener((ignored) -> {
            final JFileChooser fileChooser = new JFileChooser();
            final FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "heic", "heif");
            fileChooser.setFileFilter(filter);
            final File file;
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                file = fileChooser.getSelectedFile();
                try
                {
                    abbildung = Files.readAllBytes(Paths.get(file.getPath()));
                    updateImage();
                } catch (IOException e)
                {
                    JOptionPane.showMessageDialog(this, "Fehler beim Laden der Abbildung");
                }
            }
        });
        panel.add(abbildungsBtn, constraints);
        constraints.gridx = 2;
        final JButton ausspracheBtn = new JButton("Aussprache hinzufuegen");
        ausspracheBtn.addActionListener((ignored) -> {
        });
        panel.add(ausspracheBtn, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(new JLabel("Lautschrift"), constraints);
        lautschriftTxtField.addKeyListener(new CustomKeyListener());
        constraints.gridy = 7;
        panel.add(lautschriftTxtField, constraints);
        constraints.gridy = 8;
        panel.add(new JLabel("Verwendungshinweis"), constraints);
        constraints.gridy = 9;
        panel.add(verwendungsHinweisTxtField, constraints);
        constraints.gridy = 10;
        panel.add(new JLabel("Kategorien"), constraints);
        constraints.gridy = 11;
        final CustomButton mappingBtn = new CustomButton("Mapping");
        mappingBtn.addActionListener((ignored) ->
                steuerung.mappingGeklickt(vok, kategorien));
        panel.add(mappingBtn, constraints);
        constraints.gridy = 12;
        final CustomButton storeBtn = new CustomButton("Speichern");
        storeBtn.addActionListener((ignored) -> {
            if (wortTxtField.getText().length() > 0 && uebersetzungTxtField.getText().length() > 0)
            {
                final Vokabel localVok = new Vokabel(
                        wortTxtField.getText(),
                        uebersetzungTxtField.getText(),
                        abbildung, null,
                        lautschriftTxtField.getText(),
                        verwendungsHinweisTxtField.getText(),
                        0, 0,
                        kategorien
                );
                if (!bearbeiten)
                {
                    steuerung.vokabelHinzufuegen(localVok);
                } else
                {
                    steuerung.vokabelAendern(
                            localVok,
                            vok
                    );
                }
            }
            NavigationStack.getInstance().back();
        });
        panel.add(storeBtn, constraints);
        wortTxtField.requestFocus();
        wortTxtField.requestFocus(FocusEvent.Cause.ACTIVATION);
        return panel;
    }

    private void updateImage()
    {
        if (abbildung == null)
        {
            return;
        }
        try
        {
            final BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(abbildung));
            final ImageIcon icon = new ImageIcon(bfi);
            final Image image = icon.getImage();
            final Image scaledInstance = image.getScaledInstance(75, 75,
                    Image.SCALE_DEFAULT);
            icon.setImage(scaledInstance);
            abbildungsLabel.setIcon(icon);
        } catch (IOException ignored)
        {
            JOptionPane.showMessageDialog(this, "Fehler beim Laden der Abbildung");
        }
    }
}
