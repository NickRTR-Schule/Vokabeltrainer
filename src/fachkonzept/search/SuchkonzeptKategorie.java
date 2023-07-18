package fachkonzept.search;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;

import java.util.ArrayList;

public final class SuchkonzeptKategorie extends Suchkonzept<Kategorie>
{

    public SuchkonzeptKategorie()
            throws DatenbankAccessException, DatenbankLeseException
    {
        super();
    }

    @Override
    protected ArrayList<Kategorie> liesAlleDaten()
    {
        return Datenbank.liesKategorien();
    }

    @Override
    public ArrayList<Kategorie> suche(String text)
    {
        final ArrayList<Kategorie> arrayListSearch = new ArrayList<>();
        for (int i = 0; i < alleObjekte.size(); i++)
        {
            if (alleObjekte.get(i).liesName().toLowerCase()
                    .contains(text.toLowerCase()))
            {
                arrayListSearch.add(alleObjekte.get(i));
            }
        }
        return arrayListSearch;
    }
}
