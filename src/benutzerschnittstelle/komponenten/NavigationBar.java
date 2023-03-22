package benutzerschnittstelle.komponenten;

import steuerung.MainFrameSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A Navigation Bar
 * to use on the top
 * of every screen to navigate back
 * to the Dashboard
 */
public class NavigationBar extends JPanel
{

    public NavigationBar()
    {
        setValues();
        build();
    }

    private void setValues()
    {
        setName("Navigation Bar");
    }

    private void build()
    {
        final JButton btn = new JButton();
        btn.setText("Back"); // TODO-js: change to icon
        btn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                MainFrameSteuerung.getInstance().openDashboard();
            }
        });
        setBackground(Color.yellow);
        add(btn);
    }


}
