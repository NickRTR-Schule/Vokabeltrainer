package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class KategorielisteSteuerung
{

    public KategorielisteSteuerung()
    {

    }

    public ArrayList<Kategorie> liesKategorien() throws Exception
    {
        return Datenbank.liesKategorien();
    }
}
