package benutzerschnitstelle;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JPanel
{
	/**
	 * Create the frame.
	 */
	public Dashboard() {

		final GridBagLayout layout = new GridBagLayout();
		final GridBagConstraints constraints = new GridBagConstraints();

		// Set Data
		setName("Dashboard");

		// Content Pane
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(layout);
		
	}
}
