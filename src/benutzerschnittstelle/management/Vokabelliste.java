package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.datamangement.tablemodels.VokabelTableModel;
import steuerung.management.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public final class Vokabelliste extends ListView<Vokabel>
{

    private static final CustomButton csb = CustomButton.newVocabBtn((ignored) -> {

    });

    public Vokabelliste(Kategorie kat, ArrayList<Vokabel> vokabeln)
    {
        super(
                "Vokabeln",
                new VokabelTableModel(kat, vokabeln),
                new VokabellisteSteuerung(),
                csb
        );
        table.getColumnModel().getColumn(VokabelTableModel.EDITABLE_STATE_COLUMN_QUOTE).setCellRenderer(new DefaultTableCellRenderer()
        {


            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                setHorizontalAlignment(JLabel.RIGHT);
                Component renderComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (renderComponent instanceof JLabel)
                {
                    ((JLabel) renderComponent).setText(((JLabel) renderComponent).getText() + " %");
                }
                return renderComponent;
            }
        });
    }

    public Vokabelliste()
    {
        super("Vokabeln", new VokabelTableModel(), new VokabellisteSteuerung(), csb);
    }
}
