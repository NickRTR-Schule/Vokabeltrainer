package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import fachkonzept.search.SuchkonzeptKategorie;
import steuerung.MainFrameSteuerung;
import steuerung.management.KategorielisteSteuerung;

import java.util.ArrayList;

public final class Kategorieliste extends ListView<Kategorie>
{

    public Kategorieliste(Vokabel vok, ArrayList<Kategorie> kategorien) throws DatenbankAccessException, DatenbankLeseException
    {
        super(
                "Kategorien",
                new KategorieTableModel(kategorien),
                new KategorielisteSteuerung(),
                new SuchkonzeptKategorie(),
                getCsb(),
                true
        );
    }

    public Kategorieliste() throws DatenbankAccessException, DatenbankLeseException
    {
        super("Kategorien", new KategorieTableModel(), new KategorielisteSteuerung(), new SuchkonzeptKategorie(), getCsb(), false);
    }

    private static CustomButton getCsb()
    {
        final CustomButton btn = new CustomButton("Kategorie hinzufügen");
        btn.addActionListener((ignored) -> MainFrameSteuerung.getInstance().openKategorieuebersicht());
        return btn;
    }
}
