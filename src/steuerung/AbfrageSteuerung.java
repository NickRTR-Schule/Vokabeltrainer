package steuerung;

import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankSchreibException;
import fachkonzept.AbfrageKonzept;
import fachkonzept.navigation.NavigationStack;

public final class AbfrageSteuerung
{
    private final AbfrageKonzept dasAbfrageKonzept;

    public AbfrageSteuerung(int numberVocs, Kategorie kategorie)
    {
        dasAbfrageKonzept = new AbfrageKonzept(numberVocs, kategorie);
    }

    public void oeffnenAbgebrochen()
    {
        NavigationStack.getInstance().back();
    }

    public int anzahlVoks()
    {
        return dasAbfrageKonzept.anzahlVoks();
    }

    public Vokabel naechsteVokabel() throws EndOfAbfrageException
    {
        return dasAbfrageKonzept.naechsteVokabel();
    }

    public boolean pruefeEingabe(String eingabe) throws DatenbankAccessException,
            DatenbankSchreibException
    {
        return dasAbfrageKonzept.pruefeEingabe(eingabe);
    }

    public Vokabel liesAktuelleVokabel()
    {
        return dasAbfrageKonzept.liesAktuelleVokabel();
    }
}
