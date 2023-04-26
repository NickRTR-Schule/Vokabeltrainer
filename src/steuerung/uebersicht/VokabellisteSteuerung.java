package steuerung.uebersicht;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

import java.util.ArrayList;

public class VokabellisteSteuerung
{
    public VokabellisteSteuerung()
    {

    }

    public ArrayList<Vokabel> liesVokabeln() throws Exception
    {
        return Datenbank.liesVokabeln();
    }
}
