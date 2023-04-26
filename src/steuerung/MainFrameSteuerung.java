package steuerung;

import benutzerschnittstelle.MainFrame;
import benutzerschnittstelle.navigation.UIScreens;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

import java.awt.*;

/**
 * The Main Frame Controller controlling the Main Frame and with that Navigation
 * of this App.
 */
public final class MainFrameSteuerung
{

    /**
     * The singleton Object to access this Controller everywhere in the App
     */
    private static MainFrameSteuerung shared;

    /**
     * The Main Frame of this Application handling all the other Screens as
     * content Panes of this single Main Frame.
     * <p>
     * WARNING: Only one of this Main Frame should exist in a single Application
     * run
     */
    private final MainFrame mainFrame;

    public MainFrameSteuerung()
    {
        mainFrame = new MainFrame();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> shared = new MainFrameSteuerung());
    }

    public static MainFrameSteuerung getInstance()
    {
        return shared;
    }

    /**
     * Navigates to the Dashboard of this App
     */
    public void openDashboard()
    {
        mainFrame.open(UIScreens.Dashboard);
    }

    /**
     * Navigates to the Vokabel Abfrage Screen of this App
     */
    public void openAbfrage()
    {
        mainFrame.open(UIScreens.Abfrage);
    }

    /**
     * Navigates to the Kategorieuebersicht of this App
     */
    public void openKategorieuebersicht(Kategorie kategorie)
    {
        final Object[] args = new Object[1];
        args[0] = kategorie;
        mainFrame.open(UIScreens.Kategorieeuebersicht, args);
    }

    public void openVokabeluebersicht(Vokabel vokabel)
    {
        final Object[] args = new Object[1];
        args[0] = vokabel;
        mainFrame.open(UIScreens.Vokabeluebersicht, args);
    }

    /**
     * Navigates to the Vokabel Ersteller of this App
     */
    public void openErsteller()
    {
        mainFrame.open(UIScreens.Ersteller);
    }
    
    public void openVokabelliste()
    {
        mainFrame.open(UIScreens.Vokabelliste);
    }

    public void openKategorieliste()
    {
        mainFrame.open(UIScreens.Kategorieliste);
    }

    /**
     * Navigates to the Statistics Screen of this App
     */
    public void openStats()
    {
        mainFrame.open(UIScreens.Statistik);
    }
}
