package benutzerschnittstelle.komponenten;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomButton extends JButton
{
    public CustomButton(String title, String tooltip)
    {
        setValues(title, tooltip);
        build();
    }

    private void setValues(String title, String tooltip)
    {
        setText(title);
        setName("Button: " + title);
        setToolTipText(tooltip);
    }

    private void build()
    {
        setOpaque(true);
        setBackground(Color.red);
        setEnabled(true);
        setFocusable(true);
        setUI(new BasicButtonUI()
        {
            @Override
            public void update(Graphics g, JComponent c)
            {
                g.setColor(Color.red);
                g.setClip(new Ellipse2D.Float());
                paint(g, c);
            }
        });
    }

    @Override
    protected void paintBorder(Graphics g)
    {
        g.setColor(Color.green);
        g.drawRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
    }
}
