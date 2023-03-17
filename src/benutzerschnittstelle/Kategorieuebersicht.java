package benutzerschnittstelle;

import javax.swing.JPanel;

import datenspeicherung.Kategorie;
import steuerung.KategorieUebersichtsSteuerung;

/**
 * The Screen showing all the Categories the User created to manage and organize
 * his / her Vocabs
 */
@SuppressWarnings("serial")
public final class Kategorieuebersicht extends JPanel
{

	/**
	 * The Controller to this View
	 */
	private final KategorieUebersichtsSteuerung steuerung;

	private final Kategorie kategorie;

	public Kategorieuebersicht(Kategorie kategorie)
	{
		steuerung = new KategorieUebersichtsSteuerung();
		this.kategorie = kategorie;
	}
}
