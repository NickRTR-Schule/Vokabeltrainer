package datenbank;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class DatenbankKategorieTest
{


    @Test
    public void test() throws Exception
    {
        // Kategorie hinzufuegen

        ArrayList<Kategorie> kats = Datenbank.liesKategorien();
        assertTrue(kats.size() > 0);

        // Einzelne Kategorie lesen

        // Kategorie löschen
    }
}
