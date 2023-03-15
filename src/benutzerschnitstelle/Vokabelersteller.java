package benutzerschnitstelle;

import steuerung.KategorieUebersichtsSteuerung;
import steuerung.VokabelerstellerSteuerung;

import javax.swing.*;

/**
 * The Screen to add / create a new Vocabulary.
 */
public class Vokabelersteller extends JPanel {

    /**
     * The Controller to this View
     */
    private final VokabelerstellerSteuerung steuerung;


    public Vokabelersteller() {
        steuerung = new VokabelerstellerSteuerung();
    }
}
