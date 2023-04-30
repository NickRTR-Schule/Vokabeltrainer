package benutzerschnittstelle;

import benutzerschnittstelle.komponenten.CustomPanel;
import steuerung.StatistikSteuerung;

/**
 * The Screen showing the Stats of the User, containing learning progress, words
 * entered correctly etc...
 */
public final class Statistik extends CustomPanel
{

    /**
     * The Controller to this View
     */
    private final StatistikSteuerung steuerung;

    public Statistik()
    {
        super("Statistiken");
        steuerung = new StatistikSteuerung(this);
    }
}
