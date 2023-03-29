package fachkonzept;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;

import java.util.ArrayList;
import java.util.Collections;

public class AbfrageKonzept
{
    private final Vokabel[] voks;
    private int currentVok;

    public AbfrageKonzept(int numberVoks) throws Exception
    {
        this.voks = new Vokabel[numberVoks];
        currentVok = 0;

        voksBefuellen(numberVoks);
    }

    public int anzahlVoks()
    {
        return voks.length;
    }

    private void voksBefuellen(int numberVoks) throws Exception
    {
        ArrayList<Vokabel> vokabeln = Datenbank.liesVokabeln();
        if (vokabeln.size() < numberVoks)
        {
            numberVoks = vokabeln.size();
        }
        Collections.shuffle(vokabeln);
        for (int i = 0; i < numberVoks; i++)
        {
            voks[i] = vokabeln.get(i);
        }
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

    public boolean pruefeEingabe(String eingabe)
    {
        return eingabe.equals(voks[currentVok].liesUebersetzung());
    }

    public Vokabel liesAktuelleVokabel()
    {
        return voks[currentVok];
    }
}
