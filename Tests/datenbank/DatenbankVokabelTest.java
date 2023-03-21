package datenbank;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class DatenbankVokabelTest
{
    Datenbank derSpeicher = new Datenbank();

    @Test
    public void test() throws Exception
    {
        derSpeicher.vokabelHinzufuegen("duck", "Ente", null, null, null, null);

        ArrayList<Vokabel> vokabeln = derSpeicher.liesVokabeln();
        assertTrue(vokabeln.size() > 0);

        Vokabel vokabel = derSpeicher.liesVokabel("duck", "Ente");
        assertEquals("Ente", vokabel.liesUebersetzung());

        derSpeicher.loescheVokabel("duck", "Ente");
    }
}
