package fachkonzept;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

public final class ErstellerKonzept
{
    public void vokabelHinzufuegen(Vokabel vok) throws Exception
    {
        Datenbank.vokabelHinzufuegen(vok);
    }
}
