package fachkonzept.search;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

import java.util.ArrayList;

public final class SuchkonzeptVokabeln extends Suchkonzept<Vokabel>
{

    public SuchkonzeptVokabeln()
    {
        super();
    }

    @Override
    protected ArrayList<Vokabel> liesAlleDaten()
    {
        return Datenbank.liesVokabeln();
    }

    @Override
    public ArrayList<Vokabel> suche(String text)
    {
        final ArrayList<Vokabel> arrayListSearch = new ArrayList<>();
        for (Vokabel vokabel : alleObjekte)
        {
            if (vokabel.liesWort().toLowerCase().contains(text.toLowerCase()))
            {
                arrayListSearch.add(vokabel);
            }
        }
        return arrayListSearch;
    }
}
