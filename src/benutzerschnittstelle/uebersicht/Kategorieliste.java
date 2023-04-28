package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.komponenten.KategorieTile;
import datenspeicherung.Kategorie;
import steuerung.uebersicht.KategorielisteSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class Kategorieliste extends CustomPanel
{

    private ArrayList<Kategorie> kats;

    public Kategorieliste()
    {
        super("Kategorien");
        try
        {
            kats = new KategorielisteSteuerung().liesKategorien();
        } catch (Exception ignored)
        {
            kats = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Kategorien");
        }
        setValues();
    }

    private void setValues()
    {
        final JScrollPane contentPane = new JScrollPane();
        contentPane.setLayout(new ScrollPaneLayout());
        contentPane.setViewportView(build());
        add(contentPane);
        setName(getTitle());
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
