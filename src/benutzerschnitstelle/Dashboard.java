package benutzerschnitstelle;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JPanel
{

	private final GridBagLayout layout = new GridBagLayout();
	private final GridBagConstraints constraints = new GridBagConstraints();

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		// Set Data
		setName("Dashboard");

		// Pane Content
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(new Dimension(1000, 1000));
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		layout.setConstraints(this, constraints);
		setLayout(layout);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		final JScrollPane statsScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		statsScrollPane.setWheelScrollingEnabled(true);
		addComponent(statsScrollPane);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		final JButton abfrageBtn = new JButton();
		abfrageBtn.setText("Abfrage starten");
		addComponent(abfrageBtn);
		constraints.gridx = 5;
		final JButton vokabelErstellerBtn = new JButton();
		vokabelErstellerBtn.setText("Vokabel erstellen");
		addComponent(vokabelErstellerBtn);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 7;
		final JPanel letzteKategorienPanel = new JPanel();
		letzteKategorienPanel.setBackground(Color.GRAY);
		letzteKategorienPanel.add(new JLabel("Letzte Kategorien"));
		addComponent(letzteKategorienPanel);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 7;
		final JPanel alleKategorienPanel = new JPanel();
		alleKategorienPanel.setBackground(Color.GRAY);
		alleKategorienPanel.add(new JLabel("Alle Kategorien"));
		addComponent(alleKategorienPanel);
	}

	private void addComponent(Component component) {
		add(component, constraints);
	}
}
