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
		dasAbfrageKonzept = new AbfrageKonzept(abfrage, numberVocs);
	}

	public Vokabel naechsteVokabel() throws EndOfAbfrageException
	{
		return dasAbfrageKonzept.naechsteVokabel();
	}

	public void pruefeEingabe(String eingabe)
	{
		dasAbfrageKonzept.pruefeEingabe(eingabe);
	}
}
