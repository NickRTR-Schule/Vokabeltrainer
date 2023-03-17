package benutzerschnitstelle;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import benutzerschnitstelle.navigation.UIScreens;
import datenspeicherung.Kategorie;

/**
 * The Frame containing the other Screens as Panels in a Form of its own content
 * Pane.
 */
@SuppressWarnings("serial")
public final class MainFrame extends JFrame
{

	public MainFrame()
	{
		setValues();
		requestFocus();
	}

	/**
	 * Call all the Setter Methods to relevant variables in the init process of
	 * this Frame
	 */
	private void setValues()
	{
		setContentPane(new Dashboard());
		setOpacity(1);
		setAlwaysOnTop(false);
		setTitle("Vokabeltrainer");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 554);
		setEnabled(true);
		setLocale(Locale.GERMANY);
		setMaximumSize(new Dimension(3840, 2160));
		setMinimumSize(new Dimension(100, 100));
		setFocusable(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setName("Main Frame");
		setUndecorated(false);
		setVisible(true);
	}

	/**
	 * Navigates to another Screen in this App, being passed to this Method as
	 * the param ui
	 *
	 * @param ui
	 *            the Screen where to navigate to
	 */
	public void open(UIScreens ui)
	{
		switch (ui)
		{
			case Dashboard -> setContentPane(new Dashboard());
			case Abfrage -> setContentPane(new Abfrage());
			case Ersteller -> setContentPane(new Vokabelersteller());
			case Statistik -> setContentPane(new Statistik());
			default -> setContentPane(new ErrorScreen());
		}
	}

	/**
	 * Navigates to another Screen in the App and used the passed arguments to
	 * create the new Screen.
	 *
	 * @param ui
	 *            the Screen where to navigate to
	 * @param args
	 *            the Arguments being passed to the new Screen
	 */
	public void open(UIScreens ui, Object[] args)
	{
		if (args == null)
		{
			open(ui);
		}
		else
		{
			switch (ui)
			{
				case Abfrage:
					final int numberVocs = (int) args[0];
					setContentPane(new Abfrage(numberVocs));
					break;
				case Kategorieeuebersicht:
					final Kategorie kategorie = (Kategorie) args[0];
					setContentPane(new Kategorieuebersicht(kategorie));
					break;
				default:
					setContentPane(new ErrorScreen());
					break;
			}
		}
	}
}
