package datenspeicherung;

import java.util.ArrayList;
import java.util.Objects;

public final class Vokabel implements Comparable<Vokabel>
{
    // Vokabel-Datentyp
    private final String wort;
    private final String uebersetzung;
    private final byte[] abbildung;
    private final byte[] aussprache;
    private final String lautschrift;
    private final String verwendungshinweis;
    private ArrayList<Kategorie> kategorien;
    private int anzahlrichtig;
    private int wiederholungen;

    public Vokabel(
            String pWort,
            String pUebersetzung,
            byte[] pAbbildung,
            byte[] pAussprache,
            String pLautschrift,
            String pVerwendungshinweis,
            int pWiederholungen,
            int pAnzahlrichtig
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
        kategorien = null;
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

    public int liesQuote()
    {
        return (int) (Math.round(((double) anzahlrichtig / wiederholungen * 100) * 100) / 100);
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

    public void setzeKategorien(ArrayList<Kategorie> kats)
    {
        kategorien = kats;
    }

    public void fuegeKategorieHinzu(Kategorie kat)
    {
        kategorien.add(kat);
    }

    public void entferneKategorie(Kategorie kat)
    {
        kategorien.remove(kat);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vokabel vokabel = (Vokabel) o;
        return Objects.equals(wort, vokabel.wort) && Objects.equals(uebersetzung, vokabel.uebersetzung);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(wort, uebersetzung);
    }

    @Override
    public int compareTo(Vokabel v)
    {
        return Integer.compare(liesQuote(), v.liesQuote());
    }
}
