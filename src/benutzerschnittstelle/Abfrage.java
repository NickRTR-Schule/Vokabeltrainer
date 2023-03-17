package benutzerschnittstelle;

import javax.swing.JPanel;

import steuerung.AbfrageSteuerung;

/**
 * The Screen to test the Users knowledge and quiz the vocabs stored
 */
@SuppressWarnings("serial")
public final class Abfrage extends JPanel
{

	/**
	 * The Controller to this View
	 */
	private final AbfrageSteuerung steuerung;

	public Abfrage()
	{
		steuerung = new AbfrageSteuerung();
	}

	public Abfrage(int numberVocs)
	{
		steuerung = new AbfrageSteuerung(numberVocs);
	}
}
