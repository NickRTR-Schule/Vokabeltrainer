package fachkonzept;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankSchreibException;

import java.util.ArrayList;
import java.util.Collections;

public final class AbfrageKonzept
{
    private Vokabel[] voks;
    private int currentVok;

    public AbfrageKonzept(int numberVoks, Kategorie kategorie)
    {
        this.voks = new Vokabel[numberVoks];
        currentVok = 0;

        voksBefuellen(numberVoks, kategorie);
    }

    public int anzahlVoks()
    {
        return voks.length;
    }

    private void voksBefuellen(int numberVoks, Kategorie kategorie)
    {
        final ArrayList<Vokabel> vokabeln;
        if (kategorie != null)
        {
            vokabeln = kategorie.liesVokabeln();
        } else
        {
            vokabeln = Datenbank.liesVokabeln();
        }
        Collections.sort(vokabeln);
        if (vokabeln.size() < numberVoks)
        {
            numberVoks = vokabeln.size();
            voks = new Vokabel[numberVoks];
        }
//		Collections.shuffle(vokabeln);
        for (int i = 0; i < numberVoks; i++)
        {
            voks[i] = vokabeln.get(i);
        }
    }

    public Vokabel naechsteVokabel() throws EndOfAbfrageException
    {
        if (currentVok < voks.length - 1)
        {
            return voks[++currentVok];
        } else
        {
            throw new EndOfAbfrageException();
        }
    }

    public boolean pruefeEingabe(String eingabe) throws DatenbankAccessException, DatenbankSchreibException
    {
        boolean richtig = eingabe.equals(voks[currentVok].liesUebersetzung());
        voks[currentVok].wiederholt(richtig);
        Datenbank.vokabelWiederholt(voks[currentVok]);
        return richtig;
    }

    public Vokabel liesAktuelleVokabel()
    {
        return voks[currentVok];
    }
}
