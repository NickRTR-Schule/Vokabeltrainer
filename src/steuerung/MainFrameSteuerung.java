package steuerung;

import benutzerschnitstelle.MainFrame;
import benutzerschnitstelle.UIScreens;

import java.awt.*;

public class MainFrameSteuerung {

    private final MainFrame mainFrame;

    public MainFrameSteuerung(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            try
            {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public void openDashboard() {
        mainFrame.open(UIScreens.Dashboard);
    }

    public void openAbfrage() {
        mainFrame.open(UIScreens.Abfrage);
    }

    public void openKategorieuebersicht() {
        mainFrame.open(UIScreens.Kategorieeuebersicht);
    }


    public void openErsteller() {
        mainFrame.open(UIScreens.Ersteller);
    }

    public void openStats() {
        mainFrame.open(UIScreens.Statistik);
    }
}
