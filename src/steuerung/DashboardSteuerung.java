package steuerung;

import benutzerschnittstelle.Dashboard;
import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class DashboardSteuerung
{

    private final Dashboard dashboard;

    public DashboardSteuerung(Dashboard dashboard)
    {
        this.dashboard = dashboard;
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

}
