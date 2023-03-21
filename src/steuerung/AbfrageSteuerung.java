package steuerung;

import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;

public final class AbfrageSteuerung
{

    private final Vokabel[] voks;

    private int currentVok;


    public AbfrageSteuerung(int numberVocs)
    {
        voks = new Vokabel[numberVocs];
        currentVok = 0;
    }

    public Vokabel naechsteVokabel() throws EndOfAbfrageException
    {
        if (currentVok < voks.length)
        {
            return voks[currentVok++];
        } else
        {
            throw new EndOfAbfrageException();
        }
    }
}
