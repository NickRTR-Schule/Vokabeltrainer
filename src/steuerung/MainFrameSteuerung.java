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
     * The singleton Object
     * to access this Controller
     * everywhere in the App
     */
    private static final MainFrameSteuerung shared = new MainFrameSteuerung();

    /**
     * The Main Frame of this Application
     * handling all the other Screens as content Panes
     * of this single Main Frame.
     * <p>
     * WARNING: Only one of this Main Frame should exist in
     * a single Application run
     */
    private final MainFrame mainFrame = new MainFrame();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                final MainFrameSteuerung mainFrameSteuerung = new MainFrameSteuerung();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static MainFrameSteuerung getInstance() {
        return shared;
    }

    /**
     * Navigates to the Dashboard of this App
     */
    public void openDashboard() {
        mainFrame.open(UIScreens.Dashboard);
    }

    /**
     * Navigates to the Vokabel Abfrage Screen of this App
     */
    public void openAbfrage() {
        mainFrame.open(UIScreens.Abfrage);
    }

    /**
     * Navigates to the Kategorieuebersicht of this App
     */
    public void openKategorieuebersicht() {
        mainFrame.open(UIScreens.Kategorieeuebersicht);
    }

    /**
     * Navigates to the Vokabel Ersteller of this App
     */
    public void openErsteller() {
        mainFrame.open(UIScreens.Ersteller);
    }

    /**
     * Navigates to the Statistics Screen of this App
     */
    public void openStats() {
        mainFrame.open(UIScreens.Statistik);
    }
}
