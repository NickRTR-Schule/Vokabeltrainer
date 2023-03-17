package benutzerschnitstelle.komponenten;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Default Class for all the
 * Buttons used in this Project
 * 
 * @author jules
 *
 */
@SuppressWarnings("serial")
public final class CustomButton extends JButton {
	
	public CustomButton(String title) {
		setText(title);
		setBackground(Color.red);
	}
}
