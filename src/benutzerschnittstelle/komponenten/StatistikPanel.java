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

    private final Component component;

    public StatistikPanel(Component content)
    {
        component = content;
        build();
    }

    private void build()
    {
        setLayout(new GridLayout(1, 1));
        add(component);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                MainFrameSteuerung.getInstance().openStats();
            }
        });
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        g.setColor(Color.white);
        if (component instanceof JLabel)
        {
            CustomPainter.drawTitle(this, g, ((JLabel) component).getText());
        }
    }
}
