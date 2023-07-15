package fachkonzept.datamangement.tablemodels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Vector;

public abstract class CustomTableModel<T> extends AbstractTableModel
{

    protected final Vector<T> rows;

    protected final String[] columnNames;

    protected final Class<?>[] columnClasses;

    protected final ArrayList<Integer> editableRows;

    public CustomTableModel(String[] columnNames, Class<?>[] columnClasses, boolean edit)
    {
        this.rows = new Vector<>();
        this.editableRows = new ArrayList<>();
        final String[] localColumnNames;
        final Class<?>[] localColumnClasses;
        final int columnCountWithoutEdit = columnNames.length;
        final int columnCountWithEdit = columnCountWithoutEdit + 1;
        if (edit)
        {
            localColumnNames = new String[columnCountWithEdit];
            // TODO-js: change
            localColumnNames[0] = "Aktiv";
            System.arraycopy(columnNames, 0, localColumnNames, 1, columnCountWithoutEdit);
            localColumnClasses = new Class<?>[columnCountWithEdit];
            localColumnClasses[0] = boolean.class;
            System.arraycopy(columnClasses, 0, localColumnClasses, 1, columnCountWithoutEdit);
        } else
        {
            localColumnNames = columnNames;
            localColumnClasses = columnClasses;
        }
        this.columnNames = localColumnNames;
        this.columnClasses = localColumnClasses;
    }

    @Override
    public int getRowCount()
    {
        return rows.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public T getObjectForRow(int row)
    {
        return rows.get(row);
    }

    public void addRow(T obj)
    {
        rows.add(obj);
    }

    public void removeRow(T obj)
    {
        rows.remove(obj);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return editableRows.contains(columnIndex);
    }
}
