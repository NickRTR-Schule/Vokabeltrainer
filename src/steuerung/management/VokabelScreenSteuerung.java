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
            Vokabel vok
    )
    {
        try
        {
            dasErstellerKonzept.vokabelHinzufuegen(vok);
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

    public ArrayList<Kategorie> liesKategorien()
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
