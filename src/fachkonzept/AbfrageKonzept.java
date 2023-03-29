package fachkonzept;

import benutzerschnittstelle.Abfrage;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;

public class AbfrageKonzept
{
	private final Abfrage abfrage;
	private final Vokabel[] voks;
	private int currentVok;

	public AbfrageKonzept(Abfrage abfrage, int numberVocs)
	{
		this.abfrage = abfrage;
		this.voks = new Vokabel[numberVocs];
		currentVok = 0;
	}

	public Vokabel naechsteVokabel() throws EndOfAbfrageException
	{
		if (currentVok < voks.length)
		{
			return voks[currentVok++];
		}
		else
		{
			throw new EndOfAbfrageException();
		}
	}

	public void pruefeEingabe(String eingabe)
	{
		if (eingabe.equals(voks[currentVok].liesUebersetzung()))
		{
			abfrage.vokRichtig();
		}
		else
		{
			abfrage.vokFalsch();
		}
	}
}
