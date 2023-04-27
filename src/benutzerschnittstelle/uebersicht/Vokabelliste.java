package benutzerschnittstelle.uebersicht;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Vokabel;
import steuerung.uebersicht.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Vokabelliste extends CustomPanel
{

    private ArrayList<Vokabel> voks;

    public Vokabelliste()
    {
        super("Vokabeln");
        try
        {
            voks = new VokabellisteSteuerung().liesVokabeln();
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
        panel.add(getTable());
        return panel;
    }

    private JScrollPane getTable()
    {
        final String[] columnNames = {
                "Wort",
                "Uebersetzung",
                "Quote",
                "Wiederholungen",
                "Verwendungshinweis"
        };
        final DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        for (Vokabel vok : voks)
        {
            model.addRow(
                    new Object[]
                            {
                                    vok.liesWort(),
                                    vok.liesUebersetzung(),
                                    vok.liesQuote(),
                                    vok.liesWiederholungen(),
                                    vok.liesVerwendungshinweis(),
                            }
            );
        }
        final JScrollPane pane = new JScrollPane();
        pane.setLayout(new ScrollPaneLayout());
        pane.setViewportView(new JTable(model));
        return pane;
    }
}
