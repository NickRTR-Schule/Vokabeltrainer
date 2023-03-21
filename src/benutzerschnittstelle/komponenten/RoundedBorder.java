package benutzerschnittstelle.komponenten;

import javax.swing.border.Border;
import java.awt.*;

/**
 * The custom border class
 * to implement a rounded border
 */
public final class RoundedBorder implements Border
{
    /**
     * The Radius of this
     * rounded border, standardly
     * implemented with 25
     */
    private final int radius;

    public RoundedBorder(int radius)
    {
        this.radius = radius;
    }

    public RoundedBorder()
    {
        this(25);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        g.drawRoundRect(x, y, width, height, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(5, 5, 5, 5);
    }

    @Override
    public boolean isBorderOpaque()
    {
        return true;
    }
}
