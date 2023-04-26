package benutzerschnittstelle.komponenten;

import javax.swing.*;
import java.awt.*;

public final class CustomButton extends JButton
{

    private final String title;

    public CustomButton(String title, String tooltip)
    {
        this.title = title;
        setValues(tooltip);
        build();
    }

    public CustomButton(String title)
    {
        this(title, null);
    }

    private void setValues(String tooltip)
    {
        setText(title);
        setName("Button: " + title);
        setToolTipText(tooltip);
    }

    private void build()
    {
        setOpaque(true);
        setEnabled(true);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        g.setColor(Color.white);
        CustomPainter.drawTitle(this, g, title);
    }
}
