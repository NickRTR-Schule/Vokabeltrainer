package benutzerschnittstelle.management;

import datenspeicherung.Kategorie;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import steuerung.management.KategorielisteSteuerung;

public final class Kategorieliste extends ListView<Kategorie>
{

    public Kategorieliste()
    {
        super(
                "Kategorien",
                new KategorieTableModel(),
                new KategorielisteSteuerung()
        );
    }
}
