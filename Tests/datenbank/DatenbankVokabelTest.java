package datenbank;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class DatenbankVokabelTest
{

    @Test
    public void test() throws Exception
    {
        //Datenbank.vokabelHinzufuegen("duck", "Ente", null, null, null, null);

        ArrayList<Vokabel> vokabeln = Datenbank.liesVokabeln();
        assertTrue(vokabeln.size() > 0);

        //Vokabel vokabel = Datenbank.liesVokabel("duck", "Ente");
        //assertEquals("Ente", vokabel.liesUebersetzung());

        Datenbank.loescheVokabel("duck", "Ente");
    }
}
