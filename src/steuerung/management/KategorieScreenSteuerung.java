package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankSchreibException;
import exceptions.datenbank.DuplicateKategorieException;

import java.sql.Date;
import java.util.ArrayList;

public final class KategorieScreenSteuerung
{

    public KategorieScreenSteuerung()
    {
    }

    public void kategorieHinzufuegen(String name) throws DuplicateKategorieException, DatenbankAccessException, DatenbankSchreibException
    {
        // TODO-js: maybe provide option to create with different vocabs
        Datenbank.kategorieHinzufuegen(new Kategorie(name, new Date(System.currentTimeMillis()), new ArrayList<>()));
    }
}
