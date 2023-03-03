package datenspeicherung;


public class Vokabel {
	// Vokabel-Datentyp
	private String wort;
	private String uebersetzung;
	private byte[] abbildung;
	private byte[] aussprache;
	private String lautschrift;
	private String verwendungshinweis;
	private int wiederholungen;
	private int anzahlrichtig;
	
	public Vokabel(String pWort, String pUebersetzung, byte[] pAbbildung, byte[] pAussprache, String pLautschrift, String pVerwendungshinweis, int pWiederholungen, int pAnzahlrichtig) {
		wort = pWort;
		uebersetzung = pUebersetzung;
		abbildung = pAbbildung;
		aussprache = pAussprache;
		lautschrift = pLautschrift;
		verwendungshinweis = pVerwendungshinweis;
		wiederholungen = pWiederholungen;
		anzahlrichtig = pAnzahlrichtig;
	}
	
	// Lese-Methoden macht Lukas!
}
