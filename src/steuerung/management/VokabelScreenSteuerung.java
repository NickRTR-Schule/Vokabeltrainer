package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import fachkonzept.ErstellerKonzept;

import java.util.ArrayList;

public final class VokabelScreenSteuerung
{
    private final ErstellerKonzept dasErstellerKonzept = new ErstellerKonzept();

    public void vokabelHinzufuegen(String wort,
                                   String uebersetzung,
                                   byte[] abbildung,
                                   byte[] aussprache,
                                   String lautschrift,
                                   String verwendungshinweis,
                                   Kategorie kategorie
    )
    {
        try
        {
            dasErstellerKonzept.vokabelHinzufuegen(
                    wort,
                    uebersetzung,
                    abbildung,
                    aussprache,
                    lautschrift,
                    verwendungshinweis,
                    kategorie
            );
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public ArrayList<Kategorie> liesKategorien() throws Exception
    {
        return Datenbank.liesKategorien();
    }
}
