package fachkonzept.datamangement.tablemodels;

import datenspeicherung.Kategorie;

import java.util.ArrayList;
import java.sql.Date;

public final class KategorieTableModel extends CustomTableModel<Kategorie>
{

    /* Editable State Column Values */
    private static final int EDITABLE_STATE_COLUMN_EDIT = 0;

    private static final int EDITABLE_STATE_COLUMN_NAME = 1;

    private static final int EDITABLE_STATE_COLUMN_ZULETZT = 2;

    private static final int EDITABLE_STATE_COLUMN_VOKSANZAHL = 3;

    /* Solid State Column Values */
    private static final int SOLID_STATE_COLUMN_NAME = 0;
    private static final int SOLID_STATE_COLUMN_ZULETZT = 1;
    private static final int SOLID_STATE_COLUMN_VOKSANZAHL = 2;

    private final boolean edit;
    private final ArrayList<Kategorie> kategorien;

    private KategorieTableModel(boolean edit, ArrayList<Kategorie> kategorien)
    {
        super(
                new String[]{
                        "Name",
                        "Zuletzt Geübt",
                        "Anzahl Vokabeln"
                },
                new Class<?>[]{
                        String.class,
                        Date.class,
                        Integer.class,
                },
                edit
        );
        this.edit = edit;
        this.kategorien = kategorien;
    }

    public KategorieTableModel(ArrayList<Kategorie> kategorien)
    {
        this(true, kategorien);
    }

    public KategorieTableModel()
    {
        this(false, null);
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        final Kategorie kategorie = rows.get(row);
        final Object result;
        if (edit)
        {
            result = switch (column)
            {
                case EDITABLE_STATE_COLUMN_EDIT -> kategorien.contains(kategorie);
                case EDITABLE_STATE_COLUMN_NAME -> kategorie.liesName();
                case EDITABLE_STATE_COLUMN_ZULETZT -> kategorie.liesZuletztGeuebt();
                case EDITABLE_STATE_COLUMN_VOKSANZAHL ->
                        kategorien.contains(kategorie) ? kategorie.liesVokabeln().size() + 1 : kategorie.liesVokabeln().size();
                default -> null;
            };
        } else
        {
            result = switch (column)
            {
                case SOLID_STATE_COLUMN_NAME -> kategorie.liesName();
                case SOLID_STATE_COLUMN_ZULETZT -> kategorie.liesZuletztGeuebt();
                case SOLID_STATE_COLUMN_VOKSANZAHL -> kategorie.liesVokabeln().size();
                default -> null;
            };
        }
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (columnIndex == 0)
        {
            if ((boolean) aValue)
            {
                kategorien.add(rows.get(rowIndex));
            } else
            {
                kategorien.remove(rows.get(rowIndex));
            }
        }
    }
}
