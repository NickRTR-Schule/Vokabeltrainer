package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import fachkonzept.datamangement.tablemodels.VokabelTableModel;
import fachkonzept.search.SuchkonzeptVokabeln;
import steuerung.management.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public final class Vokabelliste extends ListView<Vokabel>
{
    private static final CustomButton csb = CustomButton.newVocabBtn((ignored) -> {

    });

    public Vokabelliste(Kategorie kat, ArrayList<Vokabel> vokabeln) throws DatenbankAccessException, DatenbankLeseException
    {
        super(
                "Vokabeln",
                new VokabelTableModel(kat, vokabeln),
                new VokabellisteSteuerung(),
                new SuchkonzeptVokabeln(),
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


                    ((JLabel) renderComponent).setText(
                            ((JLabel) renderComponent).getText()
                                    + " %");
                }
                return renderComponent;
            }
        });
    }

    public Vokabelliste() throws DatenbankAccessException, DatenbankLeseException
    {
        super("Vokabeln", new VokabelTableModel(), new VokabellisteSteuerung(), new SuchkonzeptVokabeln(), csb);
    }
}
