package benutzerschnitstelle;

import steuerung.AbfrageSteuerung;

import javax.swing.*;

/**
 * The Screen to test the Users knowledge
 * and quiz the vocabs stored
 */
public class Abfrage extends JPanel {

    /**
     * The Controller to this View
     */
    private final AbfrageSteuerung steuerung;


    public Abfrage() {
        steuerung = new AbfrageSteuerung();
    }

    public Abfrage(int numberVocs) {
        steuerung = new AbfrageSteuerung(numberVocs);
    }
}
