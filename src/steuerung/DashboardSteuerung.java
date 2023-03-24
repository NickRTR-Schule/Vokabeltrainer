package steuerung;

import benutzerschnittstelle.Dashboard;
import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class DashboardSteuerung
{

    private final Datenbank db = new Datenbank();

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

    public void statsGeklickt()
    {
        MainFrameSteuerung.getInstance().openStats();
    }

    public ArrayList<Kategorie> liesKategorien()
    {
        ArrayList<Kategorie> list;
        try
        {
            list = db.liesKategorien();
        } catch (Exception ignored)
        {
            list = new ArrayList<>();
        }
        return list;
    }

}
