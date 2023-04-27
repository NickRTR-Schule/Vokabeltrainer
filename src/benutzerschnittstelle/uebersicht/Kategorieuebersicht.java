package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Kategorie;
import steuerung.uebersicht.KategorieUebersichtsSteuerung;

/**
 * The Screen showing all the Categories the User created to manage and organize
 * his / her Vocabs
 */
public final class Kategorieuebersicht extends CustomPanel
{

    /**
     * The Controller to this View
     */
    private final KategorieUebersichtsSteuerung steuerung;

    private final Kategorie kategorie;

    public Kategorieuebersicht(Kategorie kategorie)
    {
        super("Kategorien");
        steuerung = new KategorieUebersichtsSteuerung(this);
        this.kategorie = kategorie;
    }
}
