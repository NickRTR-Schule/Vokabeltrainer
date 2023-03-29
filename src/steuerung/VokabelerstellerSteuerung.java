package steuerung;

import benutzerschnittstelle.Vokabelersteller;
import fachkonzept.ErstellerKonzept;

public final class VokabelerstellerSteuerung
{
	private ErstellerKonzept dasErstellerKonzept = new ErstellerKonzept();
	private final Vokabelersteller vokabelersteller;

	public VokabelerstellerSteuerung(Vokabelersteller ui)
	{
		vokabelersteller = ui;
	}

	public void vokabelHinzufuegen(String wort, String uebersetzung,
			byte[] abbildung, byte[] aussprache, String lautschrift,
			String verwendungshinweis)
	{
		try
		{
			dasErstellerKonzept.vokabelHinzufuegen(wort, uebersetzung,
					abbildung, aussprache, lautschrift, verwendungshinweis);
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

}
