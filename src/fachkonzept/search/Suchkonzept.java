package fachkonzept.search;

import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;

import java.util.ArrayList;

public abstract class Suchkonzept<T>
{

    protected final ArrayList<T> alleObjekte;

    public Suchkonzept() throws DatenbankAccessException, DatenbankLeseException
    {
        alleObjekte = liesAlleDaten();
    }

    protected abstract ArrayList<T> liesAlleDaten()
            throws DatenbankLeseException, DatenbankAccessException;

    public abstract ArrayList<T> suche(String text);
}
