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

    public CustomTableModel(String[] columnNames, Class<?>[] columnClasses)
    {
        this.rows = new Vector<>();
        this.columnNames = columnNames;
        this.columnClasses = columnClasses;
        this.editableRows = new ArrayList<>();
    }

    public CustomTableModel(String[] columnNames, Class<?>[] columnClasses, ArrayList<Integer> editableRows)
    {
        this.rows = new Vector<>();
        this.columnNames = columnNames;
        this.columnClasses = columnClasses;
        this.editableRows = editableRows;
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
