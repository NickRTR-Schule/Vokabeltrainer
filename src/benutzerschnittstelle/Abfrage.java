package benutzerschnittstelle;

import datenspeicherung.Vokabel;
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

    private Vokabel vok;

    public Abfrage()
    {
        this(30);
    }

    public Abfrage(int numberVocs)
    {
        steuerung = new AbfrageSteuerung(numberVocs);
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
                    if (pruefeEingabe())
                    {

                    } else
                    {

                    }
                }
            }
        });
    }

    private void frageAb()
    {
        try
        {
            vok = steuerung.naechsteVokabel();
        } catch (EndOfAbfrageException ignored)
        {
        }
        wortLabel.setText(vok.liesWort());
    }

    private boolean pruefeEingabe()
    {
        return uebersetzungField.getText().equals(vok.liesUebersetzung());
    }
}
