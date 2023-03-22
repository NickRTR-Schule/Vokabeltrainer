package benutzerschnittstelle.komponenten;

import benutzerschnittstelle.MainFrame;

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
		drawTitle(g);
	}

	// Inspiration by:
	// Discussion from: https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
	// Answer here: https://stackoverflow.com/a/27740330/16376071
	public void drawTitle(Graphics g)
	{
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(MainFrame.liesFont());
		// Determine the X coordinate for the text
		int x = (getWidth() - metrics.stringWidth(title)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
		int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
		// Draw the String
		g.drawString(title, x, y);
	}
}
