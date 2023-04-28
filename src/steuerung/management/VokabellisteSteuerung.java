package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

import java.util.ArrayList;

public final class VokabellisteSteuerung
{
    public VokabellisteSteuerung()
    {
    }

    public ArrayList<Vokabel> liesVokabeln() throws Exception
    {
        return Datenbank.liesVokabeln();
    }

    public void loescheVokabel(Vokabel vokabel) throws Exception
    {
        Datenbank.loescheVokabel(vokabel.liesWort(), vokabel.liesUebersetzung());
    }
}
