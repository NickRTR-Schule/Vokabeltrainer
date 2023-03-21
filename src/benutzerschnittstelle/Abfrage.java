package benutzerschnittstelle;

import steuerung.AbfrageSteuerung;

import javax.swing.*;

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

    public Abfrage()
    {
        this(30);
    }

    public Abfrage(int numberVocs)
    {
        steuerung = new AbfrageSteuerung(numberVocs);
        init();
    }

    private void init()
    {
        setValues();
        build();
    }

    private void setValues()
    {
        // TODO-js: set layout
        setName("Abfrage");
    }

    private void build()
    {
        add(new JLabel(""));
    }
}
