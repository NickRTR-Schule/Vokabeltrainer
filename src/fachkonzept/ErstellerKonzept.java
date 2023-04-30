package fachkonzept;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

public final class ErstellerKonzept
{
    public void vokabelHinzufuegen(String wort,
                                   String uebersetzung,
                                   byte[] abbildung,
                                   byte[] aussprache,
                                   String lautschrift,
                                   String verwendungshinweis,
                                   Kategorie kategorie
    ) throws Exception
    {

        final Vokabel vok = new Vokabel(
                wort,
                uebersetzung,
                abbildung,
                aussprache,
                lautschrift,
                verwendungshinweis,
                0,
                0
        );
        Datenbank.vokabelHinzufuegen(wort, uebersetzung, abbildung, aussprache,
                lautschrift, verwendungshinweis);
        Datenbank.vokabelZuKategorieHinzufuegen(vok, kategorie);
    }
}
