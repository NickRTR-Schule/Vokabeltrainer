package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.KategorieTile;
import datenspeicherung.Kategorie;
import steuerung.uebersicht.KategorielisteSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Kategorieliste extends JScrollPane
{
    private final KategorielisteSteuerung steuerung;

    private ArrayList<Kategorie> kats;

    public Kategorieliste()
    {
        steuerung = new KategorielisteSteuerung();
        try
        {
            kats = steuerung.liesKategorien();
        } catch (Exception ignored)
        {
            kats = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Kategorien");
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
        panel.setLayout(new GridLayout(kats.size(), 1));
        for (Kategorie kat : kats)
        {
            panel.add(new KategorieTile(kat));
        }
        return panel;
    }
}
