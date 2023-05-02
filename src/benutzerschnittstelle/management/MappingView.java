package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.datamangement.tablemodels.CustomTableModel;
import fachkonzept.datamangement.tablemodels.VokabelTableModel;
import steuerung.management.MappingSteuerung;

import javax.swing.*;
import java.awt.*;

public class MappingView extends CustomPanel
{

    private final CustomTableModel<Vokabel> vokModel;

    private final CustomTableModel<Kategorie> katModel;

    private final JTable vokTable;

    private final JTable katTable;

    private final MappingSteuerung steuerung;

    public MappingView()
    {
        super("Mapping Übersicht");
        steuerung = new MappingSteuerung();
        vokModel = new CustomTableModel<>(
                new String[]{
                        "Wort",
                        "Uebersetzung"
                },
                new Class<?>[]{
                        String.class,
                        String.class
                }
        )
        {
            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                final Vokabel vok = rows.get(rowIndex);
                return switch (columnIndex)
                {
                    case VokabelTableModel.COLUMN_WORT -> vok.liesWort();
                    case VokabelTableModel.COLUMN_UBERSETZUNG -> vok.liesUebersetzung();
                    default -> "";
                };
            }
        };
        katModel = new CustomTableModel<>(
                new String[]{
                        "Name"
                },
                new Class<?>[]{
                        String.class
                }
        )
        {
            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                return rows.get(rowIndex).liesName();
            }
        };
        vokTable = new JTable(vokModel);
        katTable = new JTable(katModel);
        setValues();
        build();
    }

    private void setValues()
    {
        setName(getTitle());
    }

    private void build()
    {
        setLayout(new GridLayout(1, 3));
        add(vokTable);
        add(katTable);
    }
}
