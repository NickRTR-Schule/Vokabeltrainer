package benutzerschnittstelle.cli;

import datenspeicherung.Datenbank;

public final class Konsole
{

    public static void main(String[] args)
    {
        try
        {
            Datenbank.vokabelHinzufuegen("duck", "Ente", null, null, null,
                    null);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
