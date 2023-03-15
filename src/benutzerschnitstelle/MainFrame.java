package benutzerschnitstelle;

import steuerung.MainFrameSteuerung;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class MainFrame extends JFrame {
    private final MainFrameSteuerung steuerung;


    public MainFrame() {
        steuerung = new MainFrameSteuerung(this);
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

    public void open(UI ui) {
        switch (ui) {
            case Dashboard -> setContentPane(new Dashboard());
            // TODO: adjust Method
        }
    }


}
