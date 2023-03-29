package fachkonzept;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import benutzerschnittstelle.Abfrage;
import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;
import exceptions.EndOfAbfrageException;

public class AbfrageKonzept
{
	private final Abfrage abfrage;
	private final Vokabel[] voks;
	private int currentVok;

	public AbfrageKonzept(Abfrage abfrage, int numberVoks) throws Exception
	{
		this.abfrage = abfrage;
		this.voks = new Vokabel[numberVoks];
		currentVok = 0;

		voksBefuellen(numberVoks);
	}

	private void voksBefuellen(int numberVoks) throws Exception
	{
		ArrayList<Vokabel> vokabeln = Datenbank.liesVokabeln();
		if (vokabeln.size() < numberVoks)
		{
			JOptionPane.showMessageDialog(abfrage,
					"Nicht ausreichend Vokabeln gespeichert.");
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
