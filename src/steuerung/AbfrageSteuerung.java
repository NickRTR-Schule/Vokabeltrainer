package steuerung;

import benutzerschnittstelle.Abfrage;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;

public final class AbfrageSteuerung
{

    private final Abfrage abfrage;

    private final Vokabel[] voks;

    private int currentVok;


    public AbfrageSteuerung(Abfrage abfrage, int numberVocs)
    {
        this.abfrage = abfrage;
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

    public void pruefeEingabe(String eingabe)
    {
        if (eingabe.equals(voks[currentVok].liesUebersetzung()))
        {
            abfrage.vokRichtig();
        } else
        {
            abfrage.vokFalsch();
        }
    }
}
