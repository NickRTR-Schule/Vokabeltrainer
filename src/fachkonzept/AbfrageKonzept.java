package fachkonzept;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import benutzerschnittstelle.Abfrage;
import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;

public class AbfrageKonzept
{
	private final Abfrage abfrage;
	private Vokabel[] voks;
	private int currentVok;

	public AbfrageKonzept(Abfrage abfrage, int numberVoks) throws Exception
	{
		if (numberVoks < 1)
		{
			throw new Error(
					"Bitte geben stellen Sie die Abfrage min. auf eine Vokabel.");
		}

		this.abfrage = abfrage;
		this.voks = new Vokabel[numberVoks];
		currentVok = 0;

		voksBefuellen(numberVoks);
	}

	private void voksBefuellen(int numberVoks) throws Exception
	{
		ArrayList<Vokabel> vokabeln = Datenbank.liesVokabeln(numberVoks);
		if (vokabeln.size() < numberVoks)
		{
			JOptionPane.showMessageDialog(abfrage,
					"Nicht ausreichend Vokabeln gespeichert.");
		}
		voks = vokabeln.toArray(new Vokabel[vokabeln.size()]);
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

	public Vokabel liesAktuelleVokabel()
	{
		return voks[currentVok];
	}
}
