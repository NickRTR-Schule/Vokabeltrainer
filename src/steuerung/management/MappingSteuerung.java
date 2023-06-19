package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class MappingSteuerung
{

    private final ArrayList<Vokabel> voks;

    private final ArrayList<Kategorie> kats;

    public MappingSteuerung() throws Exception
    {
        // TODO: work on that
        voks = Datenbank.liesVokabeln();
        kats = Datenbank.liesKategorien();
    }

    public ArrayList<Vokabel> liesVokabeln()
    {
        return voks;
    }

    public ArrayList<Vokabel> liesVokabelnFuerKategorie(Kategorie katForFilter)
    {
        // TODO: optimize
        // TODO: work on removing warning
        // TODO: not working I guess
        return kats.stream().filter(
                (kat) -> kat.equals(katForFilter)
        ).findFirst().get().liesVokabeln();
    }

    public ArrayList<Kategorie> liesKategorien()
    {
        return kats;
    }

    public ArrayList<Kategorie> liesKategorienFuerVokabel(Vokabel vokabelForFilter)
    {
        return kats.stream().filter(
                (kat) -> kat.liesVokabeln().contains(vokabelForFilter)
        ).collect(
                Collectors.toCollection(ArrayList::new)
        );
    }
}
