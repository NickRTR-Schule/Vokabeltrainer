package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class KategorielisteSteuerung implements ListenSteuerung<Kategorie>
{


    @Override
    public ArrayList<Kategorie> liesDaten() throws Exception
    {
        return Datenbank.liesKategorien();
    }

    @Override
    public void loescheDatensatz(Kategorie obj) throws Exception
    {
        Datenbank.loescheKategorie(obj.liesName());
    }
}
