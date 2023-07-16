package fachkonzept.datamangement.tablemodels;

import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

public final class VokabelTableModel extends CustomTableModel<Vokabel>
{

    /* Editable State Column Values */

    public static final int EDITABLE_STATE_COLUMN_QUOTE = 3;
    private static final int EDITABLE_STATE_COLUMN_EDIT = 0;
    private static final int EDITABLE_STATE_COLUMN_WORT = 1;
    private static final int EDITABLE_STATE_COLUMN_UEBERSETZUNG = 2;
    private static final int EDITABLE_STATE_COLUMN_WIEDERHOLUNG = 4;
    private static final int EDITABLE_STATE_COLUMN_VERWENDUNGSHINWEIS = 5;
    /* Solid State Column Values */
    private static final int SOLID_STATE_COLUMN_WORT = 0;
    private static final int SOLID_STATE_COLUMN_UBERSETZUNG = 1;
    private static final int SOLID_STATE_COLUMN_QUOTE = 2;
    private static final int SOLID_STATE_COLUMN_WIEDERHOLUNGEN = 3;
    private static final int SOLID_STATE_COLUMN_VERWENDUNGSHINWEIS = 4;

    private final boolean edit;

    private final Kategorie kat;


    private VokabelTableModel(boolean edit, Kategorie kat)
    {
        super(
                new String[]{
                        "Wort",
                        "Uebersetzung",
                        "Quote",
                        "Wiederholungen",
                        "Verwendungshinweis"
                },
                new Class<?>[]{
                        String.class,
                        String.class,
                        String.class,
                        Integer.class,
                        String.class
                },
                edit
        );
        this.edit = edit;
        this.kat = kat;
    }

    public VokabelTableModel(Kategorie kat)
    {
        this(true, kat);
    }

    public VokabelTableModel()
    {
        this(false, null);
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        final Vokabel vokabel = rows.get(row);
        final Object result;
        if (edit)
        {
            result = switch (column)
            {
                case EDITABLE_STATE_COLUMN_EDIT -> vokabel.liesKategorien().contains(kat);
                case EDITABLE_STATE_COLUMN_WORT -> vokabel.liesWort();
                case EDITABLE_STATE_COLUMN_UEBERSETZUNG -> vokabel.liesUebersetzung();
                case EDITABLE_STATE_COLUMN_QUOTE -> vokabel.liesQuote();
                case EDITABLE_STATE_COLUMN_WIEDERHOLUNG -> vokabel.liesWiederholungen();
                case EDITABLE_STATE_COLUMN_VERWENDUNGSHINWEIS -> vokabel.liesVerwendungshinweis();
                default -> null;
            };
        } else
        {
            result = switch (column)
            {
                case SOLID_STATE_COLUMN_WORT -> vokabel.liesWort();
                case SOLID_STATE_COLUMN_UBERSETZUNG -> vokabel.liesUebersetzung();
                case SOLID_STATE_COLUMN_QUOTE -> vokabel.liesQuote();
                case SOLID_STATE_COLUMN_WIEDERHOLUNGEN -> vokabel.liesWiederholungen();
                case SOLID_STATE_COLUMN_VERWENDUNGSHINWEIS -> vokabel.liesVerwendungshinweis();
                default -> null;
            };
        }
        return result;
    }
}
