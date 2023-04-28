package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Vokabel;
import steuerung.MainFrameSteuerung;
import steuerung.management.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public final class Vokabelliste extends CustomPanel
{

    final DefaultTableModel model;

    final JTable table;
    private final VokabellisteSteuerung steuerung;
    private ArrayList<Vokabel> voks;

    public Vokabelliste()
    {
        super("Vokabeln");
        model = new DefaultTableModel();
        table = new JTable();
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
        setName(getTitle());
    }

    private JPanel build()
    {
        final JPanel panel = new JPanel();
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        final GridBagLayout layout = new GridBagLayout();
        layout.setConstraints(this, constraints);
        panel.setLayout(layout);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 10;
        constraints.gridwidth = 10;
        panel.add(getTable());
        constraints.gridheight = 1;
        constraints.gridy = 20;
        panel.add(getActionPanel());
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
        for (final Vokabel vok : voks)
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
        table.setModel(model);
        pane.setViewportView(table);
        return pane;
    }

    private JPanel getActionPanel()
    {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));
        final JButton showBtn = new JButton("Show");
        showBtn.addActionListener((ignored) -> MainFrameSteuerung.getInstance().openVokabeluebersicht(getCurrentVok()));
        panel.add(showBtn);
        final JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener((ignored) -> {
            try
            {
                steuerung.loescheVokabel(getCurrentVok());
            } catch (Exception ig)
            {
                JOptionPane.showMessageDialog(this, "Fehler beim loeschen der Vokabel");
            }
            voks.remove(table.getSelectedRow());
            model.fireTableRowsUpdated(0, voks.size());
        });
        panel.add(deleteBtn);
        return panel;
    }

    private Vokabel getCurrentVok()
    {
        final int row = table.getSelectedRow();
        return new Vokabel(
                (String) model.getValueAt(row, 0),
                (String) model.getValueAt(row, 1),
                (byte[]) model.getValueAt(row, 2),
                (byte[]) model.getValueAt(row, 3),
                (String) model.getValueAt(row, 4),
                (String) model.getValueAt(row, 5),
                (int) model.getValueAt(row, 6),
                (int) model.getValueAt(row, 7)
        );
    }
}
