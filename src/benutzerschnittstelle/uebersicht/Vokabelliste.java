package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.Vokabeltile;
import datenspeicherung.Vokabel;
import steuerung.uebersicht.VokabellisteSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vokabelliste extends JPanel
{

    private final VokabellisteSteuerung steuerung;

    private ArrayList<Vokabel> voks;

    public Vokabelliste()
    {
        steuerung = new VokabellisteSteuerung();
        // TODO: work on
        try
        {
            voks = steuerung.liesVokabeln();
        } catch (Exception ignored)
        {
            voks = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Vokabeln");
        }
    }

    private void build()
    {
        setLayout(new GridLayout(voks.size(), 1));
        for (Vokabel vok : voks)
        {
            add(new Vokabeltile(vok));
        }
    }
}
