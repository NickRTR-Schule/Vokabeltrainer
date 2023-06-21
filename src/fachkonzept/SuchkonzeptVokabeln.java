package fachkonzept;

import java.util.ArrayList;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;

public final class SuchkonzeptVokabeln extends Suchkonzept<Vokabel>
{

	public SuchkonzeptVokabeln()
			throws DatenbankAccessException, DatenbankLeseException
	{
		super();
	}

	@Override
	protected ArrayList<Vokabel> liesAlleDaten()
			throws DatenbankAccessException, DatenbankLeseException
	{
		return Datenbank.liesVokabeln();
	}

	@Override
	public ArrayList<Vokabel> suche(String text)
	{
		final ArrayList<Vokabel> arrayListSearch = new ArrayList<>();
		for (int i = 0; i < alleObjekte.size(); i++)
		{
			if (alleObjekte.get(i).liesWort().toLowerCase()
					.contains(text.toLowerCase()))
			{
				arrayListSearch.add(alleObjekte.get(i));
			}
		}
		return arrayListSearch;
	}
}