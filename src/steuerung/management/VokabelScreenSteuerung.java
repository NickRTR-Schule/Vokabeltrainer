package steuerung.management;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

public final class VokabelScreenSteuerung
{
	public VokabelScreenSteuerung()
	{
	}

	public void vokabelHinzufuegen(Vokabel dieVokabel)
	{
		try
		{
			Datenbank.vokabelHinzufuegen(dieVokabel);
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void vokabelAendern(Vokabel dieVokabel, Vokabel alteVokabel)
	{
		try
		{
			Datenbank.loescheVokabel(alteVokabel.liesWort(),
					alteVokabel.liesUebersetzung());
			Datenbank.vokabelHinzufuegen(dieVokabel);
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
}
