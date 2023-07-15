package steuerung;

import benutzerschnittstelle.MainFrame;
import benutzerschnittstelle.navigation.UIScreens;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import fachkonzept.navigation.NavigationStack;

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
     * The Main Frame of this Application handling all the other Screens as content
     * Panes of this single Main Frame.
     * <p>
     * WARNING: Only one of this Main Frame should exist in a single Application run
     */
    private final MainFrame mainFrame;

    private MainFrameSteuerung()
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

    private void openForward(UIScreens screen)
    {
        NavigationStack.getInstance().forward(screen);
    }

    private void openForward(UIScreens screen, Object obj)
    {
        NavigationStack.getInstance().forward(screen, obj);
    }

    /**
     * Navigates to the Dashboard of this App
     */
    public void openDashboard()
    {
        final UIScreens dashboard = UIScreens.Dashboard;
        openForward(dashboard);
        mainFrame.open(dashboard);
    }

    /**
     * Navigates to the Vokabel Abfrage Screen of this App
     */
    public void openAbfrage()
    {
        final UIScreens screen = UIScreens.Abfrage;
        openForward(screen);
        mainFrame.open(screen);
    }

    public void openUebersicht(Object obj)
    {
        if (obj instanceof Vokabel)
        {
            openVokabeluebersicht((Vokabel) obj);
        } else if (obj instanceof Kategorie)
        {
            openKategorieuebersicht((Kategorie) obj);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * WARNING! Only use in Navigation Stack
     *
     * @param screen
     * @param obj
     */
    public void open(UIScreens screen, Object obj)
    {
        mainFrame.open(screen, obj);
    }

    /**
     * Navigates to the KategorieScreen of this App
     */
    private void openKategorieuebersicht(Kategorie kategorie)
    {
        final UIScreens screen = UIScreens.Kategorieeuebersicht;
        openForward(screen, kategorie);
        mainFrame.open(screen, kategorie);
    }

    public void openKategorieuebersicht()
    {
        final UIScreens screen = UIScreens.Kategorieeuebersicht;
        openForward(screen);
        mainFrame.open(screen);
    }

    private void openVokabeluebersicht(Vokabel vokabel)
    {
        final UIScreens screen = UIScreens.Vokabeluebersicht;
        openForward(screen, vokabel);
        mainFrame.open(screen, vokabel);
    }

    /**
     * Navigates to the Vokabel Ersteller of this App
     */
    public void openErsteller()
    {
        final UIScreens screen = UIScreens.Ersteller;
        openForward(screen);
        mainFrame.open(screen);
    }

    public void openVokabelliste()
    {
        final UIScreens screen = UIScreens.Vokabelliste;
        openForward(screen);
        mainFrame.open(screen);
    }

    public void openKategorieliste()
    {
        final UIScreens screen = UIScreens.Kategorieliste;
        openForward(screen);
        mainFrame.open(screen);
    }

    /**
     * Navigates to the Statistics Screen of this App
     */
    public void openStats()
    {
        final UIScreens screen = UIScreens.Statistik;
        openForward(screen);
        mainFrame.open(screen);
    }
}
