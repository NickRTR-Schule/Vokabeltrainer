package fachkonzept.search;

import java.util.ArrayList;

public abstract class Suchkonzept<T>
{

    protected final ArrayList<T> alleObjekte;

    public Suchkonzept()
    {
        alleObjekte = liesAlleDaten();
    }

    protected abstract ArrayList<T> liesAlleDaten();

    public abstract ArrayList<T> suche(String text);
}
