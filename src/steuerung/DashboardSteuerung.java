package steuerung;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class DashboardSteuerung
{

    public DashboardSteuerung()
    {
    }

    public void abfrageGeklickt()
    {
        MainFrameSteuerung.getInstance().openAbfrage();
    }

    public void erstellerGeklickt()
    {
        MainFrameSteuerung.getInstance().openErsteller();
    }

    public void vokabellisteGeklickt()
    {
        MainFrameSteuerung.getInstance().openVokabelliste();
    }

    public void kategorielisteGeklickt()
    {
        MainFrameSteuerung.getInstance().openKategorieliste();
    }

    public void statsGeklickt()
    {
        MainFrameSteuerung.getInstance().openStats();
    }

    public ArrayList<Kategorie> liesKategorien()
    {
        ArrayList<Kategorie> list;
        try
        {
            list = Datenbank.liesKategorien();
        } catch (Exception ignored)
        {
            list = new ArrayList<>();
        }
        return list;
    }

    public void mappingGeklickt()
    {
        MainFrameSteuerung.getInstance().openMappingUebersicht();
    }
}
