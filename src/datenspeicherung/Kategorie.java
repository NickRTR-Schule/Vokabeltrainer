package datenspeicherung;

import java.util.ArrayList;

public final class Kategorie
{
    private final String name;
    private final ArrayList<Vokabel> vokabeln;

    public Kategorie(String name, ArrayList<Vokabel> vokabeln)
    {
        this.name = name;
        this.vokabeln = vokabeln;
    }

    public String liesName()
    {
        return name;
    }

    public ArrayList<Vokabel> liesVokabeln()
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
