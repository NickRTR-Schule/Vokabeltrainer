package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import steuerung.management.KategorielisteSteuerung;

public final class Kategorieliste extends ListView<Kategorie>
{

    public Kategorieliste(boolean edit)
    {
        super(
                "Kategorien",
                new KategorieTableModel(),
                new KategorielisteSteuerung(),
                new CustomButton("Test"),
                edit
        );
    }

    public Kategorieliste()
    {
        this(false);
    }
}
