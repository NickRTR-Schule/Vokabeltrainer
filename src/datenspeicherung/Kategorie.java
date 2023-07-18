package datenspeicherung;

import java.util.ArrayList;
import java.util.Objects;

public final class Kategorie
{
    private final String name;
    private final ArrayList<Vokabel> vokabeln;

    public Kategorie(String name)
    {
        this.name = name;
        vokabeln = new ArrayList<>();
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kategorie kategorie = (Kategorie) o;
        return Objects.equals(name, kategorie.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
