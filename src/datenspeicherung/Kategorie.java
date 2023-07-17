package datenspeicherung;

import java.util.ArrayList;

public final class Kategorie
{
    private final String name;
    private ArrayList<Vokabel> vokabeln;

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

    public void entferneVokabel(Vokabel vok)
    {
        vokabeln.remove(vok);
    }

    public void fuegeVokabelHinzu(Vokabel vok)
    {
        vokabeln.add(vok);
    }

    public void aendereVokabeln(ArrayList<Vokabel> voks)
    {
        vokabeln = voks;
    }
}
