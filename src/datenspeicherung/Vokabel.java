package datenspeicherung;

public final class Vokabel
{
	// Vokabel-Datentyp
	private String wort;
	private String uebersetzung;
	private byte[] abbildung;
	private byte[] aussprache;
	private String lautschrift;
	private String verwendungshinweis;
	private int wiederholungen;
	private int anzahlrichtig;

	public Vokabel(String pWort, String pUebersetzung, byte[] pAbbildung,
			byte[] pAussprache, String pLautschrift, String pVerwendungshinweis,
			int pWiederholungen, int pAnzahlrichtig)
	{
		wort = pWort;
		uebersetzung = pUebersetzung;
		abbildung = pAbbildung;
		aussprache = pAussprache;
		lautschrift = pLautschrift;
		verwendungshinweis = pVerwendungshinweis;
		wiederholungen = pWiederholungen;
		anzahlrichtig = pAnzahlrichtig;
	}

	public String liesWort()
	{
		return wort;
	}

	public String liesUebersetzung()
	{
		return uebersetzung;
	}

	public byte[] liesAbbildung()
	{
		return abbildung;
	}

	public byte[] liesAussprache()
	{
		return aussprache;
	}

	public String liesLautschrift()
	{
		return lautschrift;
	}

	public String liesVerwendungshinweis()
	{
		return verwendungshinweis;
	}

	public int liesWiederholungen()
	{
		return wiederholungen;
	}

	public int liesAnzahlRichtig()
	{
		return anzahlrichtig;
	}

	public void wiederholt(boolean richtig)
	{
		wiederholungen++;
		if (richtig)
			anzahlrichtig++;
	}
}
