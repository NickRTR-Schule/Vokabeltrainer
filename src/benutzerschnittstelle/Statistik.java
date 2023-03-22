package benutzerschnittstelle;

import steuerung.StatistikSteuerung;

import javax.swing.*;

/**
 * The Screen showing the Stats of the User, containing learning progress, words
 * entered correctly etc...
 */
public final class Statistik extends JPanel
{

    /**
     * The Controller to this View
     */
    private final StatistikSteuerung steuerung;

    public Statistik()
    {
        steuerung = new StatistikSteuerung(this);
    }
}
