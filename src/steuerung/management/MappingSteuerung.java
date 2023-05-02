package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

import java.util.ArrayList;

public class MappingSteuerung
{
    public ArrayList<Vokabel> liesVokabeln() throws Exception
    {
        return Datenbank.liesVokabeln();
    }

    public ArrayList<Kategorie> liesKategorien() throws Exception
    {
        return Datenbank.liesKategorien();
    }
}
