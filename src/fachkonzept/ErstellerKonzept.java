package fachkonzept;

import datenspeicherung.Datenbank;

public class ErstellerKonzept
{
	private Datenbank dieDatenbank = new Datenbank();

	public void vokabelHinzufuegen(String wort, String uebersetzung,
			byte[] abbildung, byte[] aussprache, String lautschrift,
			String verwendungshinweis) throws Exception
	{
		dieDatenbank.vokabelHinzufuegen(wort, uebersetzung, abbildung,
				aussprache, lautschrift, verwendungshinweis);
	}
}
