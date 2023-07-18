package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

import java.util.ArrayList;

public final class VokabellisteSteuerung implements ListenSteuerung<Vokabel>
{

    @Override
    public ArrayList<Vokabel> liesDaten()
    {
        return Datenbank.liesVokabeln();
    }

    @Override
    public void loescheDatensatz(Vokabel obj) throws Exception
    {
        Datenbank.loescheVokabel(obj.liesWort(), obj.liesUebersetzung());
    }
}
