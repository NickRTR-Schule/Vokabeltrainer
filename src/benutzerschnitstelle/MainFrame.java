package benutzerschnitstelle;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 *  The Frame containing the
 *  other Screens as Panels
 *  in a Form of its own
 *  content Pane.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setValues();
        requestFocus();
    }

    /**
     * Call all the Setter Methods
     * to relevant variables in the init
     * process of this Frame
     */
    private void setValues() {
        setContentPane(new Dashboard());
        setOpacity(1);
        setAlwaysOnTop(false);
        setTitle("Vokabeltrainer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 554);
        setEnabled(true);
        setLocale(Locale.GERMANY);
        setMaximumSize(new Dimension(3840, 2160));
        setMinimumSize(new Dimension(100, 100));
        setFocusable(true);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setName("Main Frame");
        setUndecorated(false);
    }

    /**
     * Navigates to another Screen in this App,
     * being passed to this Method as the param ui
     *
     * @param ui the Screen where to navigate to
     */
    public void open(UIScreens ui) {
        switch (ui) {
            case Dashboard -> setContentPane(new Dashboard());
            case Abfrage -> setContentPane(new Abfrage());
            case Ersteller -> setContentPane(new Vokabelersteller());
            case Statistik -> setContentPane(new Statistik());
            case Kategorieeuebersicht -> setContentPane(new Kategorieuebersicht());
        }
    }

    // TODO-js: implement navigation stack?
}
