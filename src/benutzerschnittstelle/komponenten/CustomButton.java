package benutzerschnittstelle.komponenten;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Default Class for all the Buttons used in this Project
 *
 * @author jules
 */
@SuppressWarnings("serial")
public final class CustomButton extends JButton
{

    public CustomButton(String title)
    {
        setText(title);
        setBackground(Color.RED);
        setBorder(new RoundedBorder(25));
        setOpaque(true);
        setEnabled(true);
    }
}

// Discussion from:
// https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
// Answer from: https://stackoverflow.com/a/3634480/16376071
class RoundedBorder implements Border
{

    private final int radius;

    public RoundedBorder(int radius)
    {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
                            int height)
    {
        g.drawRoundRect(x, y, width, height, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public boolean isBorderOpaque()
    {
        return false;
    }
}
