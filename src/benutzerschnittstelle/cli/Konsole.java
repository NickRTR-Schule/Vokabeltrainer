package benutzerschnittstelle.cli;

import datenspeicherung.Datenbank;

public final class Konsole
{

    public static void main(String[] args)
    {
        try
        {
            // ArrayList<Vokabel> vokabeln = derSpeicher.liesVokabeln();
            // System.out.println(vokabeln);

            // Vokabel vokabel = derSpeicher.liesVokabel("cat", "Katze");
            // System.out.println(vokabel.liesUebersetzung());

            // derSpeicher.loescheVokabel("cat", "Katze");

            Datenbank.vokabelHinzufuegen("duck", "Ente", null, null, null,
                    null);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
