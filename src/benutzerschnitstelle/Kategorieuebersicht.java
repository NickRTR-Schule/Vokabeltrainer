package benutzerschnitstelle;

import steuerung.AbfrageSteuerung;
import steuerung.KategorieUebersichtsSteuerung;

import javax.swing.*;

/**
 * The Screen showing all the Categories
 * the User created to manage and organize
 * his / her Vocabs
 */
public class Kategorieuebersicht extends JPanel {

    /**
     * The Controller to this View
     */
    private final KategorieUebersichtsSteuerung steuerung;


    public Kategorieuebersicht() {
        steuerung = new KategorieUebersichtsSteuerung();
    }
}
