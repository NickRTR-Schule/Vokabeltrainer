package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Kategorie;
import steuerung.management.KategorieUebersichtsSteuerung;

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
        super(kategorie.liesName());
        steuerung = new KategorieUebersichtsSteuerung();
        this.kategorie = kategorie;
        build();
    }

    private void build()
    {

    }
}
