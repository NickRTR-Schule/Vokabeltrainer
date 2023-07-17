package steuerung;

import benutzerschnittstelle.MainFrame;
import benutzerschnittstelle.komponenten.MappingWindow;
import benutzerschnittstelle.navigation.UIScreens;
import datenspeicherung.Datenbank;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import fachkonzept.navigation.NavigationStack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        EventQueue.invokeLater(() -> {
            shared = new MainFrameSteuerung();
            try
            {
                shared.initDatabase();
                for (Kategorie kat : Datenbank.liesKategorien())
                {
                    System.out.println(kat.liesName());
                    for (Vokabel vok : Datenbank.liesVokabelnForKat(kat.liesName()))
                    {
                        System.out.println(" " + vok.liesWort());
                    }

                }
            } catch (Exception ignored)
            {
                JOptionPane.showMessageDialog(shared.mainFrame, "Fehler beim Laden der Datenbank");
            }
        });
    }

    public static MainFrameSteuerung getInstance()
    {
        return shared;
    }

    public void initDatabase() throws DatenbankAccessException, DatenbankLeseException
    {
        Datenbank.init();
    }

    /* Navigation Stack Methods */

    private void openForward(UIScreens screen)
    {
        NavigationStack.getInstance().forward(screen);
    }

    private void openForward(UIScreens screen, Object obj)
    {
        NavigationStack.getInstance().forward(screen, obj);
    }

    /**
     * WARNING!
     * Only use in Navigation Stack
     *
     * @param screen - The Screen to navigate to
     * @param obj    - the Object that can be passed to the Screen you want to
     *               navigate to
     */
    public void openForNavigationStack(UIScreens screen, Object obj)
    {
        mainFrame.open(screen, obj);
    }

    /* Open Methods to open different Screens inside the Application */

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

    public void openMapping(Class<?> oClass, ArrayList<?> objects)
    {
        final MappingWindow<?, ?> window = new MappingWindow<>(oClass, objects);
        window.pack();
        window.setVisible(true);
    }

    public void openMapping(Object object, ArrayList<?> objects)
    {
        final MappingWindow<?, ?> window = new MappingWindow<>(object, objects);
        window.pack();
        window.setVisible(true);
    }
}
