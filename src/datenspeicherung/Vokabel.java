package datenspeicherung;


public final class Vokabel
{
    // Vokabel-Datentyp
    private final String wort;
    private final String uebersetzung;
    private final byte[] abbildung;
    private final byte[] aussprache;
    private final String lautschrift;
    private final String verwendungshinweis;
    private final int wiederholungen;
    private final int anzahlrichtig;

    public Vokabel(String pWort, String pUebersetzung, byte[] pAbbildung, byte[] pAussprache, String pLautschrift, String pVerwendungshinweis, int pWiederholungen, int pAnzahlrichtig)
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
}
