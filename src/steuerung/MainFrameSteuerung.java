package steuerung;

import benutzerschnitstelle.MainFrame;
import benutzerschnitstelle.UIScreens;

import java.awt.*;

/**
 * The Main Frame Controller controlling
 * the Main Frame and with that Navigation
 * of this App.
 */
public class MainFrameSteuerung {

    /**
     * The Main Frame of this Application
     * handling all the other Screens as content Panes
     * of this single Main Frame.
     * <p>
     * WARNING: Only one of this Main Frame should exist in
     * a single Application run
     */
    private static final MainFrame mainFrame = new MainFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            try
            {
                mainFrame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    /**
     * Navigates to the Dashboard of this App
     */
    public static void openDashboard() {
        mainFrame.open(UIScreens.Dashboard);
    }

    /**
     * Navigates to the Vokabel Abfrage Screen of this App
     */
    public static void openAbfrage() {
        mainFrame.open(UIScreens.Abfrage);
    }

    /**
     * Navigates to the Kategorieuebersicht of this App
     */
    public static void openKategorieuebersicht() {
        mainFrame.open(UIScreens.Kategorieeuebersicht);
    }

    /**
     * Navigates to the Vokabel Ersteller of this App
     */
    public static void openErsteller() {
        mainFrame.open(UIScreens.Ersteller);
    }

    /**
     * Navigates to the Statistics Screen of this App
     */
    public static void openStats() {
        mainFrame.open(UIScreens.Statistik);
    }
}
