package benutzerschnittstelle;

import datenspeicherung.Kategorie;
import steuerung.KategorieUebersichtsSteuerung;

import javax.swing.*;

/**
 * The Screen showing all the Categories the User created to manage and organize
 * his / her Vocabs
 */
public final class Kategorieuebersicht extends JPanel
{

    /**
     * The Controller to this View
     */
    private final KategorieUebersichtsSteuerung steuerung;

    private final Kategorie kategorie;

    public Kategorieuebersicht(Kategorie kategorie)
    {
        steuerung = new KategorieUebersichtsSteuerung(this);
        this.kategorie = kategorie;
    }
}
