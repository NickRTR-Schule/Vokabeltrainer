package benutzerschnittstelle.management;

import datenspeicherung.Kategorie;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import fachkonzept.SuchkonzeptKategorie;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import steuerung.management.KategorielisteSteuerung;

public final class Kategorieliste extends ListView<Kategorie>
{

	public Kategorieliste()
			throws DatenbankAccessException, DatenbankLeseException

	{
		super("Kategorien", new KategorieTableModel(),
				new KategorielisteSteuerung(), new SuchkonzeptKategorie());
	}
}
