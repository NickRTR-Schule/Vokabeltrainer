package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.datamangement.tablemodels.KategorieTableModel;
import steuerung.management.KategorielisteSteuerung;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Kategorie> getSelectedObjects()
    {
        final ArrayList<Kategorie> kats = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++)
        {
            if ((boolean) model.getValueAt(i, 0))
            {
                kats.add(model.getObjectForRow(i));
            }
        }
        return kats;
    }
}
