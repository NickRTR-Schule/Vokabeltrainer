package benutzerschnittstelle;

import exceptions.EndOfAbfrageException;
import steuerung.AbfrageSteuerung;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Screen to test the Users knowledge and quiz the vocabs stored
 */
@SuppressWarnings("serial")
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
        this(30);
    }

    public Abfrage(int numberVocs)
    {
        steuerung = new AbfrageSteuerung(this, numberVocs);
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
        // TODO-js: Zeige Meldung
    }

    public void vokRichtig()
    {
        // TODO-js: Zeige Meldung
        frageAb();
    }
}
