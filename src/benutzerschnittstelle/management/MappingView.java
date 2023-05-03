package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.datamangement.tablemodels.CustomTableModel;
import steuerung.management.MappingSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MappingView extends CustomPanel
{

    private final CustomTableModel<Vokabel> vokModel;

    private final CustomTableModel<Kategorie> katModel;

    private final JTable vokTable;

    private final JTable katTable;
    private final HashMap<Vokabel, ArrayList<Kategorie>> vokabelLookUp;
    private final HashMap<Kategorie, ArrayList<Vokabel>> kategorieLookUp;
    private boolean vokEditing = false;
    private boolean katEditing = false;
    private ArrayList<Kategorie> kats;
    private MappingSteuerung steuerung;
    private ArrayList<Vokabel> voks;
    private Kategorie selectedKat;
    private Vokabel selectedVok;

    public MappingView()
    {
        super("Mapping Übersicht");
        vokabelLookUp = new HashMap<>();
        kategorieLookUp = new HashMap<>();
        vokModel = new CustomTableModel<>(
                new String[]{
                        "Ausgewaehlt",
                        "Wort",
                        "Uebersetzung"
                },
                new Class<?>[]{
                        Boolean.class,
                        String.class,
                        String.class
                },
                new ArrayList<>(List.of(0))
        )
        {
            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                final Vokabel vok = rows.get(rowIndex);
                return switch (columnIndex)
                {
                    case 0 -> katEditing ? kategorieLookUp.get(selectedKat).contains(vok) : null;
                    case 1 -> vok.liesWort();
                    case 2 -> vok.liesUebersetzung();
                    default -> "";
                };
            }
        };
        katModel = new CustomTableModel<>(
                new String[]{
                        "Ausgewaehlt",
                        "Name"
                },
                new Class<?>[]{
                        Boolean.class,
                        String.class
                },
                new ArrayList<>(List.of(0))
        )
        {
            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                final Kategorie kat = rows.get(rowIndex);
                return switch (columnIndex)
                {
                    case 0 -> vokEditing ? vokabelLookUp.get(selectedVok).contains(kat) : null;
                    case 1 -> kat.liesName();
                    default -> "";
                };
            }
        };
        vokTable = new JTable(vokModel);
        katTable = new JTable(katModel);
        try
        {
            steuerung = new MappingSteuerung();
        } catch (Exception ignored)
        {
            JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Daten");
            return;
        }
        voks = steuerung.liesVokabeln();
        kats = steuerung.liesKategorien();
        setValues();
        add(build());
    }

    private void setValues()
    {
        setName(getTitle());
    }

    private JPanel build()
    {
        for (final Vokabel vok : voks)
        {
            vokModel.addRow(vok);
        }
        for (final Kategorie kat : kats)
        {
            katModel.addRow(kat);
        }
        final JScrollPane vokPane = new JScrollPane();
        final JScrollPane katPane = new JScrollPane();
        vokPane.setLayout(new ScrollPaneLayout());
        katPane.setLayout(new ScrollPaneLayout());
        vokTable.getTableHeader().setReorderingAllowed(false);
        katTable.getTableHeader().setReorderingAllowed(false);
        katTable.getSelectionModel().addListSelectionListener(e -> {
            katEditing = true;
            selectedKat = katModel.getObjectForRow(katTable.getSelectedRow());
            kategorieLookUp.put(selectedKat, steuerung.liesVokabelnFuerKategorie(selectedKat));
            vokModel.fireTableDataChanged();
        });
        vokTable.getSelectionModel().addListSelectionListener(e -> {
            vokEditing = true;
            selectedVok = vokModel.getObjectForRow(vokTable.getSelectedRow());
            vokabelLookUp.put(selectedVok, steuerung.liesKategorienFuerVokabel(selectedVok));
            katModel.fireTableDataChanged();
        });
        vokPane.setViewportView(vokTable);
        katPane.setViewportView(katTable);
        vokPane.setBorder(BorderFactory.createEmptyBorder());
        katPane.setBorder(BorderFactory.createEmptyBorder());
        final JPanel panel = new JPanel();
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(25, 25, 25, 25);
        final GridBagLayout layout = new GridBagLayout();
        layout.setConstraints(this, constraints);
        panel.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 8;
        panel.add(katPane, constraints);
        constraints.gridx = 9;
        constraints.gridwidth = 1;
        panel.add(new JPanel(), constraints);
        constraints.gridwidth = 8;
        constraints.gridx = 10;
        panel.add(vokPane, constraints);
        return panel;
    }
}
