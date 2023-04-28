package fachkonzept;

import datenspeicherung.Datenbank;

public final class ErstellerKonzept
{
    public void vokabelHinzufuegen(String wort, String uebersetzung,
                                   byte[] abbildung, byte[] aussprache, String lautschrift,
                                   String verwendungshinweis) throws Exception
    {
        Datenbank.vokabelHinzufuegen(wort, uebersetzung, abbildung, aussprache,
                lautschrift, verwendungshinweis);
    }
}
