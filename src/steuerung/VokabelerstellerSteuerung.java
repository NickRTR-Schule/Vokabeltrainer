package steuerung;

import fachkonzept.ErstellerKonzept;

public final class VokabelerstellerSteuerung
{
    private final ErstellerKonzept dasErstellerKonzept = new ErstellerKonzept();

    public VokabelerstellerSteuerung()
    {
    }

    public void vokabelHinzufuegen(String wort, String uebersetzung,
                                   byte[] abbildung, byte[] aussprache, String lautschrift,
                                   String verwendungshinweis)
    {
        try
        {
            dasErstellerKonzept.vokabelHinzufuegen(wort, uebersetzung,
                    abbildung, aussprache, lautschrift, verwendungshinweis);
        } catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
