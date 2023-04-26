package benutzerschnittstelle;

import benutzerschnittstelle.error.ErrorScreen;
import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.navigation.UIScreens;
import benutzerschnittstelle.uebersicht.Kategorieuebersicht;
import datenspeicherung.Kategorie;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;

/**
 * The Frame containing the other Screens as Panels in a Form of its own content
 * Pane.
 */
public final class MainFrame extends JFrame
{

    private static final Font font = new Font("Roboto", Font.PLAIN, 12);

    public MainFrame()
    {
        setValues();
        requestFocus();
    }

    public static Font liesFont()
    {
        return font;
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
        setFont(font);
    }

    /**
     * Navigates to another Screen in this App, being passed to this Method as
     * the param ui
     *
     * @param ui the Screen where to navigate to
     */
    public void open(UIScreens ui)
    {
        switch (ui)
        {
            case Dashboard -> setContentPane(new Dashboard());
            case Abfrage -> setContentPane(new CustomPanel(new Abfrage()));
            case Ersteller -> setContentPane(new CustomPanel(new Vokabelersteller()));
            case Statistik -> setContentPane(new CustomPanel(new Statistik()));
            default -> setContentPane(new CustomPanel(new ErrorScreen()));
        }
        update();
    }

    /**
     * Updates the Main Frame and all it's content
     */
    private void update()
    {
        revalidate();
        repaint();
    }

    /**
     * Navigates to another Screen in the App and used the passed arguments to
     * create the new Screen.
     *
     * @param ui   the Screen where to navigate to
     * @param args the Arguments being passed to the new Screen
     */
    public void open(UIScreens ui, Object[] args)
    {
        if (args == null)
        {
            open(ui);
        } else
        {
            if (Objects.requireNonNull(ui) == UIScreens.Kategorieeuebersicht)
            {
                final Kategorie kategorie = (Kategorie) args[0];
                setContentPane(new CustomPanel(new Kategorieuebersicht(kategorie)));
            } else
            {
                setContentPane(new CustomPanel(new ErrorScreen()));
            }
        }
        update();
    }
}
