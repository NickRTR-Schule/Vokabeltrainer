package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import fachkonzept.search.SuchkonzeptKategorie;
import steuerung.management.KategorielisteSteuerung;

import java.util.ArrayList;

public final class Kategorieliste extends ListView<Kategorie>
{

    private static final CustomButton csb = new CustomButton("Kategorie hinzufügen");

    public Kategorieliste(Vokabel vok, ArrayList<Kategorie> kategorien) throws DatenbankAccessException, DatenbankLeseException
    {
        super(
                "Kategorien",
                new KategorieTableModel(vok, kategorien),
                new KategorielisteSteuerung(),
                new SuchkonzeptKategorie(),
                csb
        );
    }

    public Kategorieliste() throws DatenbankAccessException, DatenbankLeseException
    {
        super("Kategorien", new KategorieTableModel(), new KategorielisteSteuerung(), new SuchkonzeptKategorie(), csb);
    }
}
