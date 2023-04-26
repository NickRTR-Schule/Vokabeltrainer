package steuerung;

import exceptions.datenbank.*;

import benutzerschnittstelle.Abfrage;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;
import fachkonzept.AbfrageKonzept;

public final class AbfrageSteuerung
{
    private AbfrageKonzept dasAbfrageKonzept;

    public AbfrageSteuerung(Abfrage abfrage, int numberVocs)
    {
        try
        {
            dasAbfrageKonzept = new AbfrageKonzept(numberVocs);
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public int anzahlVoks()
    {
        return dasAbfrageKonzept.anzahlVoks();
    }

    public Vokabel naechsteVokabel() throws EndOfAbfrageException
    {
        return dasAbfrageKonzept.naechsteVokabel();
    }

    public boolean pruefeEingabe(String eingabe)  throws DatenbankAccessException,
	DatenbankSchreibException
    {
        return dasAbfrageKonzept.pruefeEingabe(eingabe);
    }

    public Vokabel liesAktuelleVokabel()
    {
        return dasAbfrageKonzept.liesAktuelleVokabel();
    }
}
