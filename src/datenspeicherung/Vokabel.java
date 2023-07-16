package datenspeicherung;

import java.util.ArrayList;

public final class Vokabel
{
    // Vokabel-Datentyp
    private final String wort;
    private final String uebersetzung;
    private final byte[] abbildung;
    private final byte[] aussprache;
    private final String lautschrift;
    private final String verwendungshinweis;
    private int anzahlrichtig;
    private int wiederholungen;

    private final ArrayList<Kategorie> kategorien;

    public Vokabel(String pWort, String pUebersetzung, byte[] pAbbildung, byte[] pAussprache, String pLautschrift, String pVerwendungshinweis, int pWiederholungen, int pAnzahlrichtig)
    {
        this(pWort, pUebersetzung, pAbbildung, pAussprache, pLautschrift, pVerwendungshinweis, pWiederholungen, pAnzahlrichtig, new ArrayList<>());
    }

    public Vokabel(
            String pWort,
            String pUebersetzung,
            byte[] pAbbildung,
            byte[] pAussprache,
            String pLautschrift,
            String pVerwendungshinweis,
            int pWiederholungen,
            int pAnzahlrichtig,
            ArrayList<Kategorie> kategorien
    )
    {
        wort = pWort;
        uebersetzung = pUebersetzung;
        abbildung = pAbbildung;
        aussprache = pAussprache;
        lautschrift = pLautschrift;
        verwendungshinweis = pVerwendungshinweis;
        wiederholungen = pWiederholungen;
        anzahlrichtig = pAnzahlrichtig;
        this.kategorien = kategorien;
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

    public long liesQuote()
    {
        return Math.round(((double) anzahlrichtig / wiederholungen * 100) * 100) / 100;
    }

    public void wiederholt(boolean richtig)
    {
        wiederholungen++;
        if (richtig)
            anzahlrichtig++;
    }

    public ArrayList<Kategorie> liesKategorien()
    {
        return kategorien;
    }

    public void fuegeKategorieHinzu(Kategorie kat)
    {
        kategorien.add(kat);
    }

    public void entferneKategorie(Kategorie kat)
    {
        kategorien.remove(kat);
    }
}
