package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import steuerung.management.KategorielisteSteuerung;

public final class Kategorieliste extends ListView<Kategorie>
{

    private static final CustomButton csb = new CustomButton("Kategorie hinzufügen");

    public Kategorieliste(Vokabel vok)
    {
        super(
                "Kategorien",
                new KategorieTableModel(vok),
                new KategorielisteSteuerung(),
                csb
        );
    }

    public Kategorieliste()
    {
        super("Kategorien", new KategorieTableModel(), new KategorielisteSteuerung(), csb);
    }
}
