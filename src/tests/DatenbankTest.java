package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

public final class DatenbankTest {
	Datenbank derSpeicher = new Datenbank();
	
	@Test
	public void Test() throws Exception
	{
		derSpeicher.vokabelHinzufuegen("duck", "Ente", null, null, null, null);
		
		ArrayList<Vokabel> vokabeln = derSpeicher.liesVokabeln();
		assertTrue(vokabeln.size() > 0);
		
		Vokabel vokabel = derSpeicher.liesVokabel("duck", "Ente");
		assertEquals("Ente", vokabel.liesUebersetzung());
		
		derSpeicher.loescheVokabel("duck", "Ente");
	}
}
