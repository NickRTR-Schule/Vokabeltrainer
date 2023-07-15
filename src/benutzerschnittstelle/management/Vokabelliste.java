package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
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
        this(false);
    }


    public Vokabelliste(boolean edit)
    {
        super(
                "Vokabeln",
                new VokabelTableModel(edit),
                new VokabellisteSteuerung(),
                CustomButton.newVocabBtn((ignored) -> {
                }),
                edit
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
