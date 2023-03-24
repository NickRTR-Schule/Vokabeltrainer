package benutzerschnittstelle;

import exceptions.EndOfAbfrageException;
import steuerung.AbfrageSteuerung;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Screen to test the Users knowledge and quiz the vocabs stored
 */
public final class Abfrage extends JPanel
{

    /**
     * The Controller to this View
     */
    private final AbfrageSteuerung steuerung;

    private final JLabel wortLabel;

    private final JTextField uebersetzungField;


    public Abfrage()
    {
        steuerung = new AbfrageSteuerung(this, frageVokabelAnzahl());
        // Init Components
        wortLabel = new JLabel();
        uebersetzungField = new JTextField();
        setValues();
        build();
    }

    private void setValues()
    {
        setName("Abfrage");
    }

    private void build()
    {
        // TODO-js: set layout
        add(wortLabel);
        add(uebersetzungField);
        uebersetzungField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    steuerung.pruefeEingabe(uebersetzungField.getText());
                }
            }
        });
    }

    private int frageVokabelAnzahl()
    {
        return Integer.parseInt(JOptionPane.showInputDialog("Wie viele Vokabeln sollen abgefragt werden?", 30));
    }

    private void frageAb()
    {
        try
        {
            wortLabel.setText(steuerung.naechsteVokabel().liesWort());
        } catch (EndOfAbfrageException ignored)
        {
        }

    }

    public void vokFalsch()
    {
        JOptionPane.showMessageDialog(this, "Deine Eingabe ist leider falsch. Versuche es noch einmal");
    }

    public void vokRichtig()
    {
        JOptionPane.showMessageDialog(this, "Richtig! Auf zur nächsten Vokabel");
        frageAb();
    }
}
