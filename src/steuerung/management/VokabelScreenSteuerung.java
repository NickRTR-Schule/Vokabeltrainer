package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.ErstellerKonzept;
import steuerung.MainFrameSteuerung;

import java.util.ArrayList;

public final class VokabelScreenSteuerung
{

    private final ErstellerKonzept dasErstellerKonzept;

    public VokabelScreenSteuerung()
    {
        dasErstellerKonzept = new ErstellerKonzept();
    }

    public void vokabelHinzufuegen(
            String wort,
            String uebersetzung,
            byte[] abbildung,
            byte[] aussprache,
            String lautschrift,
            String verwendungshinweis
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
                    verwendungshinweis
            );
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void mappingGeklickt(Vokabel vok, ArrayList<Kategorie> kats)
    {
        if (vok == null)
        {
            MainFrameSteuerung.getInstance().openMapping(Vokabel.class, kats);
        } else
        {
            MainFrameSteuerung.getInstance().openMapping(vok, kats);
        }

    }

    public ArrayList<Kategorie> liesKategorien() throws Exception
    {
        return Datenbank.liesKategorien();
    }

    public void vokabelAendern(Vokabel dieVokabel, Vokabel alteVokabel)
    {
        try
        {
            Datenbank.loescheVokabel(alteVokabel.liesWort(),
                    alteVokabel.liesUebersetzung());
            Datenbank.vokabelHinzufuegen(dieVokabel);
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
