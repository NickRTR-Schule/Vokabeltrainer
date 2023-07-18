package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;

import java.util.ArrayList;

public final class KategorieScreenSteuerung
{

    public KategorieScreenSteuerung()
    {
    }

    public void kategorieHinzufuegen(String name)
    {
        // TODO-js: maybe provide option to create with different vocabs
        Datenbank.kategorieHinzufuegen(new Kategorie(name, new ArrayList<>()));
    }
}
