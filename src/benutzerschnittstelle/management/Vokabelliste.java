package benutzerschnittstelle.management;

import datenspeicherung.Vokabel;
import fachkonzept.datamangement.tablemodels.VokabelTableModel;
import steuerung.management.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public final class Vokabelliste extends ListView<Vokabel>
{

    public Vokabelliste()
    {
        super(
                "Vokabeln",
                new VokabelTableModel(),
                new VokabellisteSteuerung()
        );
        table.getColumnModel().getColumn(VokabelTableModel.COLUMN_QUOTE).setCellRenderer(new DefaultTableCellRenderer()
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
}