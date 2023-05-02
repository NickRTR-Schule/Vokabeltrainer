package datenspeicherung;

public final class Kategorie
{
    private final String name;
    private final Vokabel[] vokabeln;

    public Kategorie(String name, Vokabel[] vokabeln)
    {
        this.name = name;
        this.vokabeln = vokabeln;
    }

    public String liesName()
    {
        return name;
    }

    public Vokabel[] liesVokabeln()
    {
        return vokabeln;
    }

    public void entferneVokabel()
    {

    }

    public void fuegeVokabelHinzu()
    {

    }

    public void aendereVokabel()
    {

    }
}
