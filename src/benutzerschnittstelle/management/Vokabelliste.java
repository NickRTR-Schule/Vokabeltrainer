package benutzerschnittstelle.management;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import datenspeicherung.Vokabel;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import fachkonzept.SuchkonzeptVokabeln;
import fachkonzept.datamangement.tablemodels.VokabelTableModel;
import steuerung.management.VokabellisteSteuerung;

public final class Vokabelliste extends ListView<Vokabel>
{

	public Vokabelliste()
			throws DatenbankAccessException, DatenbankLeseException
	{
		super("Vokabeln", new VokabelTableModel(), new VokabellisteSteuerung(),
				new SuchkonzeptVokabeln());
		table.getColumnModel().getColumn(VokabelTableModel.COLUMN_QUOTE)
				.setCellRenderer(new DefaultTableCellRenderer()
				{

					@Override
					public Component getTableCellRendererComponent(JTable table,
							Object value, boolean isSelected, boolean hasFocus,
							int row, int column)
					{
						setHorizontalAlignment(JLabel.RIGHT);
						Component renderComponent = super.getTableCellRendererComponent(
								table, value, isSelected, hasFocus, row,
								column);
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
}
