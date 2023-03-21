package datenbank;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatenbankKategorieTest
{

    final Datenbank derSpeicher = new Datenbank();

    @Test
    public void test() throws Exception
    {
        // Kategorie hinzufuegen

        ArrayList<Kategorie> kats = derSpeicher.liesKategorien();
        assertTrue(kats.size() > 0);

        // Einzelne Kategorie lesen

        // Kategorie löschen
    }
}
