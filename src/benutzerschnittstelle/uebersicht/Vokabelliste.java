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

    public Vokabelliste(Vokabel[] voks)
    {
        steuerung = new VokabellisteSteuerung();
        this.voks = voks;
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
