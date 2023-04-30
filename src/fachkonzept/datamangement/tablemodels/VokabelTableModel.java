package fachkonzept.datamangement.tablemodels;

import datenspeicherung.Vokabel;

import java.util.Vector;

public class VokabelTableModel extends CustomTableModel<Vokabel>
{

    public static final int COLUMN_QUOTE = 2;
    private static final int COLUMN_WORT = 0;
    private static final int COLUMN_UBERSETZUNG = 1;
    private static final int COLUMN_WIEDERHOLUNGEN = 3;
    private static final int COLUMN_VERWENDUNGSHINWEIS = 4;


    public VokabelTableModel()
    {
        super(
                new Vector<>(),
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
                }
        );
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        final Vokabel vokabel = rows.get(row);
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
}