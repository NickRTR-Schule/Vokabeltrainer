package fachkonzept.datamangement.tablemodels;

import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class KategorieTableModel extends CustomTableModel<Kategorie>
{

    /* Editable State Column Values */
    private static final int EDITABLE_STATE_COLUMN_EDIT = 0;

    private static final int EDITABLE_STATE_COLUMN_NAME = 1;

    private static final int EDITABLE_STATE_COLUMN_VOKSANZAHL = 2;

    /* Solid State Column Values */
    private static final int SOLID_STATE_COLUMN_NAME = 0;
    private static final int SOLID_STATE_COLUMN_VOKSANZAHL = 1;
    private final Vokabel vok;
    private final boolean edit;

    private final Map<Kategorie, List<Vokabel>> ausgewaelteVokabeln = new HashMap<>();

    private KategorieTableModel(boolean edit, Vokabel vok)
    {
        super(
                new String[]{
                        "Name",
                        "Anzahl Vokabeln"
                },
                new Class<?>[]{
                        String.class,
                        Integer.class
                },
                edit
        );
        this.edit = edit;
        this.vok = vok;
    }

    public KategorieTableModel(Vokabel vok)
    {
        this(true, vok);
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
            List<Vokabel> ausgewaehlteVokelnFuerKategorie = ausgewaelteVokabeln.get(kategorie);
            if (ausgewaehlteVokelnFuerKategorie == null)
            {
                ausgewaehlteVokelnFuerKategorie = new ArrayList<>(kategorie.liesVokabeln());
                ausgewaelteVokabeln.put(kategorie, ausgewaehlteVokelnFuerKategorie);
            }
            result = switch (column)
            {
                case EDITABLE_STATE_COLUMN_EDIT -> ausgewaehlteVokelnFuerKategorie.contains(vok);
                case EDITABLE_STATE_COLUMN_NAME -> kategorie.liesName();
                case EDITABLE_STATE_COLUMN_VOKSANZAHL -> ausgewaehlteVokelnFuerKategorie.size();
                default -> null;
            };
        } else
        {
            result = switch (column)
            {
                case SOLID_STATE_COLUMN_NAME -> kategorie.liesName();
                case SOLID_STATE_COLUMN_VOKSANZAHL -> kategorie.liesVokabeln().size();
                default -> null;
            };
        }
        return result;
    }
}
