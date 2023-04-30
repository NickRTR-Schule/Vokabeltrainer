package benutzerschnittstelle.management;

import datenspeicherung.Vokabel;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class VokabelTableModel extends AbstractTableModel
{

    public static final int COLUMN_QUOTE = 2;
    private static final int COLUMN_WORT = 0;
    private static final int COLUMN_UBERSETZUNG = 1;
    private static final int COLUMN_WIEDERHOLUNGEN = 3;
    private static final int COLUMN_VERWENDUNGSHINWEIS = 4;
    final String[] columnNames = {
            "Wort",
            "Uebersetzung",
            "Quote",
            "Wiederholungen",
            "Verwendungshinweis"
    };

    final Class[] columnClass = {
            String.class,
            String.class,
            String.class,
            Integer.class,
            String.class
    };

    private final Vector<Vokabel> rows = new Vector<>();


    @Override
    public boolean isCellEditable(int row, int column)
    {
        return super.isCellEditable(row, column);
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getRowCount()
    {
        return rows.size();
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        Vokabel vokabel = rows.get(row);
        return switch (column)
        {
            case COLUMN_WORT -> vokabel.liesWort();
            case COLUMN_UBERSETZUNG -> vokabel.liesUebersetzung();
            case COLUMN_QUOTE -> vokabel.liesQuote();
            case COLUMN_WIEDERHOLUNGEN -> vokabel.liesWiederholungen();
            case COLUMN_VERWENDUNGSHINWEIS -> vokabel.liesVerwendungshinweis();
            default -> null;
        };
    }

    public Vokabel getVokabelForRow(int row)
    {
        return rows.get(row);

    }

    public void addRow(Vokabel vokabel)
    {
        rows.add(vokabel);
    }

    public void removeRow(Vokabel vokabel)
    {
        rows.remove(vokabel);
    }
}
