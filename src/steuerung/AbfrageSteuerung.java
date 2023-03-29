package steuerung;

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
			dasAbfrageKonzept = new AbfrageKonzept(abfrage, numberVocs);
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	public Vokabel naechsteVokabel() throws EndOfAbfrageException
	{
		return dasAbfrageKonzept.naechsteVokabel();
	}

	public void pruefeEingabe(String eingabe)
	{
		dasAbfrageKonzept.pruefeEingabe(eingabe);
	}

	public Vokabel liesAktuelleVokabel()
	{
		return dasAbfrageKonzept.liesAktuelleVokabel();
	}
}
