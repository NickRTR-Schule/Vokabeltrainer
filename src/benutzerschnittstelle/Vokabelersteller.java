package benutzerschnittstelle;

import javax.swing.JPanel;

import steuerung.VokabelerstellerSteuerung;

/**
 * The Screen to add / create a new Vocabulary.
 */
@SuppressWarnings("serial")
public final class Vokabelersteller extends JPanel
{

	/**
	 * The Controller to this View
	 */
	private final VokabelerstellerSteuerung steuerung;

	public Vokabelersteller()
	{
		steuerung = new VokabelerstellerSteuerung();
	}
}
