package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.Vokabeltile;
import datenspeicherung.Vokabel;
import steuerung.uebersicht.VokabellisteSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vokabelliste extends JScrollPane
{

    private final VokabellisteSteuerung steuerung;

    private ArrayList<Vokabel> voks;

    public Vokabelliste()
    {
        steuerung = new VokabellisteSteuerung();
        try
        {
            voks = steuerung.liesVokabeln();
        } catch (Exception ignored)
        {
            voks = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Vokabeln");
        }
        setValues();
    }

    private void setValues()
    {
        setLayout(new ScrollPaneLayout());
        setViewportView(build());
        setName("Vokabelliste");
    }

    private JPanel build()
    {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(voks.size(), 1));
        for (Vokabel vok : voks)
        {
            panel.add(new Vokabeltile(vok));
        }
        return panel;
    }
}
