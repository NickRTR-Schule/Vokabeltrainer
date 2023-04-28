package benutzerschnittstelle.komponenten;

import benutzerschnittstelle.MainFrame;

import java.awt.*;

public final class CustomPainter
{
    // Inspiration by:
    // Discussion from: https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
    // Answer here: https://stackoverflow.com/a/27740330/16376071
    public static void drawTitle(Component component, Graphics g, String title)
    {
        // Get the FontMetrics
        final FontMetrics metrics = g.getFontMetrics(MainFrame.liesFont());
        // Determine the X coordinate for the text
        final int x = (component.getWidth() - metrics.stringWidth(title)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        final int y = (component.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        // Draw the String
        g.drawString(title, x, y);
    }
}
