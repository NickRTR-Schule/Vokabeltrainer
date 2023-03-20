package steuerung;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class DashboardSteuerung
{

    private final Datenbank db = new Datenbank();

    public void abfrageGeklickt()
    {
        MainFrameSteuerung.getInstance().openAbfrage(30);
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
