package benutzerschnittstelle;

import javax.swing.JPanel;

import steuerung.StatistikSteuerung;

/**
 * The Screen showing the Stats of the User, containing learning progress, words
 * entered correctly etc...
 */
@SuppressWarnings("serial")
public final class Statistik extends JPanel
{

	/**
	 * The Controller to this View
	 */
	private final StatistikSteuerung steuerung;

	public Statistik()
	{
		steuerung = new StatistikSteuerung();
	}
}
