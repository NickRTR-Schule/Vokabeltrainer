package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Vokabel;
import steuerung.MainFrameSteuerung;
import steuerung.management.VokabellisteSteuerung;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public final class Vokabelliste extends CustomPanel
{

    private final VokabelTableModel model;

    private final JTable table;
    private final VokabellisteSteuerung steuerung;
    private ArrayList<Vokabel> voks;

    public Vokabelliste()
    {
        super("Vokabeln");
        model = new VokabelTableModel();
        table = new JTable(model);
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
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        panel.add(getTable(), constraints);
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridy = 1;
        panel.add(getActionPanel(), constraints);
        return panel;
    }

    private JScrollPane getTable()
    {
        table.setDragEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        for (final Vokabel vok : voks)
        {
            model.addRow(vok);

        }
        final JScrollPane pane = new JScrollPane();
        pane.setLayout(new ScrollPaneLayout());
        pane.setBorder(BorderFactory.createEmptyBorder());
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
        final RowSorter<? extends TableModel> sorter = table.getRowSorter();
        return model.getVokabelForRow(sorter != null ? sorter.convertRowIndexToModel(table.getSelectedRow()) : row);
    }
}
