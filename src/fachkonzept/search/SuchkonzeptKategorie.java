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
        for (Kategorie kategorie : alleObjekte)
        {
            if (kategorie.liesName().toLowerCase()
                    .contains(text.toLowerCase()))
            {
                arrayListSearch.add(kategorie);
            }
        }
        return arrayListSearch;
    }
}
