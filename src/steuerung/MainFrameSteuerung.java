package steuerung;

import benutzerschnitstelle.Dashboard;
import benutzerschnitstelle.MainFrame;
import benutzerschnitstelle.UI;

import javax.swing.*;

public class MainFrameSteuerung {

    private final MainFrame mainFrame;

    public MainFrameSteuerung(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void openDashboard() {
        mainFrame.open(UI.Dashboard);
    }

    public void openAbfrage() {
    }
}
