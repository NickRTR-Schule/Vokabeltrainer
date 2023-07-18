package datenspeicherung;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Objects;

public final class Kategorie
{
    private final String name;
    private Date zuletztGeuebt;
    private final ArrayList<Vokabel> vokabeln;

    public Kategorie(String name, Date zuletztGeuebt)
    {
        this.name = name;
        this.zuletztGeuebt = zuletztGeuebt;
        vokabeln = new ArrayList<>();
    }

    public Kategorie(String name, Date zuletztGeuebt, ArrayList<Vokabel> vokabeln)
    {
        this.name = name;
        this.zuletztGeuebt = zuletztGeuebt;
        this.vokabeln = vokabeln;
    }

    public String liesName()
    {
        return name;
    }

    public Date liesZuletztGeuebt()
    {
        return zuletztGeuebt;
    }

    public ArrayList<Vokabel> liesVokabeln()
    {
        return vokabeln;
    }

    public void setzeZuletztGeuebt(Date zuletztGeuebt)
    {
        this.zuletztGeuebt = zuletztGeuebt;
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
