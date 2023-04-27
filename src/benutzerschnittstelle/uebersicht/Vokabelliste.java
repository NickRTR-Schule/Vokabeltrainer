package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.komponenten.Vokabeltile;
import datenspeicherung.Vokabel;
import steuerung.uebersicht.VokabellisteSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vokabelliste extends CustomPanel
{

    private final VokabellisteSteuerung steuerung;

    private ArrayList<Vokabel> voks;

    public Vokabelliste()
    {
        super("Vokabeln");
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
        final JScrollPane contentPane = new JScrollPane();
        contentPane.setLayout(new ScrollPaneLayout());
        contentPane.setViewportView(build());
        add(contentPane);
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

    private JTable getTable()
    {
        final JTable table = new JTable();
        return table;
    }
}
