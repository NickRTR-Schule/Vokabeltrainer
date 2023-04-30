package fachkonzept.datamangement.tablemodels;

import datenspeicherung.Kategorie;

import java.util.Vector;

public class KategorieTableModel extends CustomTableModel<Kategorie>
{

    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_VOKSANZAHL = 1;

    public KategorieTableModel()
    {
        super(
                new Vector<>(),
                new String[]{
                        "Name",
                        "Anzahl Vokabeln"
                },
                new Class<?>[]{
                        String.class,
                        Integer.class
                }
        );
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        final Kategorie kategorie = rows.get(row);
        return switch (column)
        {
            case COLUMN_NAME -> kategorie.liesName();
            case COLUMN_VOKSANZAHL -> kategorie.liesVokabeln().length;
            default -> null;
        };
    }
}
