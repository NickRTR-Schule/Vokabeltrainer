package benutzerschnittstelle.management;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.TableModel;

import benutzerschnittstelle.komponenten.CustomPanel;
import fachkonzept.datamangement.tablemodels.CustomTableModel;
import fachkonzept.search.Suchkonzept;
import steuerung.DashboardSteuerung;
import steuerung.MainFrameSteuerung;
import steuerung.management.ListenSteuerung;
import steuerung.management.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ListView<T> extends CustomPanel
{
    public final JTable table;
    private final CustomTableModel<T> model;
    private final ListenSteuerung<T> steuerung;
    private final JTextField searchField;
    private final Suchkonzept<T> suchkonzept;
    private ArrayList<T> objects;

    public ListView(String name, CustomTableModel<T> model,
                    ListenSteuerung<T> steuerung, Suchkonzept<T> suchkonzept)
    {
        super("Vokabeln");
        this.steuerung = steuerung;
        try
        {
            objects = steuerung.liesDaten();
        } catch (Exception ignored)
        {
            objects = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Fehler beim laden der Daten");
        }
        this.model = model;
        searchField = new JTextField();
        this.suchkonzept = suchkonzept;
        // searchField.addActionListener((e) -> geklicktSuche(name));
        searchField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                geklicktSuche(searchField.getText());
            }
        });
        table = new JTable(model);
        init();
    }

	private void init() {
		setBackground(Color.WHITE);
		add(build());
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
        constraints.gridy = 0;
        constraints.gridx = 1;
        panel.add(searchField, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        panel.add(getTable(), constraints);
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridy = 2;
        panel.add(getActionPanel(), constraints);
        return panel;
    }

	private JScrollPane getTable() {
		table.setDragEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		for (final T obj : objects) {
			model.addRow(obj);

		}
		final JScrollPane pane = new JScrollPane();
		pane.setLayout(new ScrollPaneLayout());
		pane.setBorder(BorderFactory.createEmptyBorder());
		pane.setViewportView(table);
		return pane;
	}

    private void updateTable(ArrayList<T> newObjects)
    {
        for (int i = 0; i < model.getRowCount(); i++)
        {
            model.removeRow(model.getObjectForRow(i));
        }
        for (final T obj : newObjects)
        {
            model.addRow(obj);
        }
    }

    private void resetTable()
    {
        updateTable(objects);
    }

    public void geklicktSuche(String text)
    {
        updateTable(suchkonzept.suche(text));
        model.fireTableDataChanged();
    }
    
	private JPanel getActionPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 4));
		final JButton showBtn = new JButton("Show");
		showBtn.addActionListener((ignored) -> MainFrameSteuerung.getInstance().openUebersicht(getCurrentObject()));
		panel.add(showBtn);
		final JButton addBtn = new JButton("Add");
		if (steuerung instanceof VokabellisteSteuerung) {
			addBtn.addActionListener((ignored) -> new DashboardSteuerung().erstellerGeklickt());
		} else {
			addBtn.addActionListener((ignored) -> MainFrameSteuerung.getInstance().openKategorieuebersicht());
		}
		panel.add(addBtn);
		final JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener((ignored) -> {
			try {
				final T obj = getCurrentObject();
				steuerung.loescheDatensatz(obj);
				model.removeRow(obj);
			} catch (Exception ig) {
				JOptionPane.showMessageDialog(this, "Fehler beim loeschen der Vokabel");
			}
			model.fireTableRowsUpdated(0, objects.size());
			objects.remove(table.getSelectedRow());
		});
		panel.add(deleteBtn);
		return panel;
	}

	private T getCurrentObject() {
		final int row = table.getSelectedRow();
		final RowSorter<? extends TableModel> sorter = table.getRowSorter();
		return model.getObjectForRow(sorter != null ? sorter.convertRowIndexToModel(table.getSelectedRow()) : row);
	}
}
