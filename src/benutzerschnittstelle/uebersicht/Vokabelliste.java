package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.Vokabeltile;
import datenspeicherung.Vokabel;
import steuerung.uebersicht.VokabellisteSteuerung;

import javax.swing.*;
import java.awt.*;

public class Vokabelliste extends JPanel
{

    private final VokabellisteSteuerung steuerung;

    private final Vokabel[] voks;

    public Vokabelliste()
    {
        steuerung = new VokabellisteSteuerung();
        // TODO: work on
        voks = new Vokabel[1];
    }

    private void build()
    {
        setLayout(new GridLayout(voks.length, 1));
        for (Vokabel vok : voks)
        {
            add(new Vokabeltile(vok));
        }
    }
}
