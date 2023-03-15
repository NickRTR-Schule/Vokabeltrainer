package benutzerschnitstelle;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class MainFrame extends JFrame {

    public MainFrame() {
        setContentPane(new Dashboard());
        setOpacity(1);
        setAlwaysOnTop(false);
        setTitle("Vokabeltrainer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 554);
        requestFocus();
        setEnabled(true);
        setLocale(Locale.GERMANY);
        setMaximumSize(new Dimension(3840, 2160));
        setMinimumSize(new Dimension(100, 100));
        setFocusable(true);
    }



    public void open(UIScreens ui) {
        switch (ui) {
            case Dashboard -> setContentPane(new Dashboard());
            case Abfrage -> setContentPane(new Abfrage());
            case Ersteller -> setContentPane(new VokabelErsteller());
            case Statistik -> setContentPane(new Statistik());
            case Kategorieeuebersicht -> setContentPane(new Kategorieuebersicht());
        }
    }

    // TODO-js: implement stack?
}
