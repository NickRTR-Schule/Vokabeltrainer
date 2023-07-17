package fachkonzept;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

public final class ErstellerKonzept
{
    public void vokabelHinzufuegen(String wort,
                                   String uebersetzung,
                                   byte[] abbildung,
                                   byte[] aussprache,
                                   String lautschrift,
                                   String verwendungshinweis
    ) throws Exception
    {
        Datenbank.vokabelHinzufuegen(new Vokabel(
                wort,
                uebersetzung,
                abbildung,
                aussprache,
                lautschrift,
                verwendungshinweis,
                0,
                0)
        );
    }
}
