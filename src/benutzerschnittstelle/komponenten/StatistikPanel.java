package benutzerschnittstelle.komponenten;

import steuerung.MainFrameSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel representing a single
 * Statistic
 */
public final class StatistikPanel extends JPanel
{
    public StatistikPanel(Component content)
    {
        build(content);
    }

    private void build(Component content)
    {
        setLayout(new GridLayout(1, 1));
        setBorder(new RoundedBorder());
        add(content);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                MainFrameSteuerung.getInstance().openStats();
            }
        });
    }
}
