package benutzerschnittstelle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import benutzerschnittstelle.komponenten.CustomButton;
import exceptions.EndOfAbfrageException;
import steuerung.AbfrageSteuerung;

/**
 * The Screen to test the Users knowledge and quiz the vocabs stored
 */
public final class Abfrage extends JPanel
{

	/**
	 * The Controller to this View
	 */
	private final AbfrageSteuerung steuerung;

	private final JLabel wortLabel;

	private final JTextField uebersetzungField;

	private final int enteredNumberVoks;

	public Abfrage()
	{
		enteredNumberVoks = frageVokabelAnzahl();
		steuerung = new AbfrageSteuerung(this, enteredNumberVoks);
		// Init Components
		wortLabel = new JLabel("Wort", SwingConstants.CENTER);
		uebersetzungField = new JTextField();
		uebersetzungField.setHorizontalAlignment(JTextField.CENTER);
		setValues();
		build();
	}

	private void setValues()
	{
		setName("Abfrage");
	}

	private void build()
	{
		final GridBagLayout layout = new GridBagLayout();
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(15, 0, 15, 0);
		layout.setConstraints(this, constraints);
		constraints.weightx = .5;
		setLayout(layout);
		constraints.gridx = 0;
		add(new JPanel(), constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(wortLabel, constraints);
		constraints.gridy = 1;
		add(uebersetzungField, constraints);
		final CustomButton checkBtn = new CustomButton("Check");
		checkBtn.addActionListener((e) -> {
			checkVok();
		});
		constraints.gridy = 2;
		add(checkBtn, constraints);
		constraints.gridx = 3;
		add(new JPanel(), constraints);
		uebersetzungField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					checkVok();
				}
			}
		});
		if (steuerung.anzahlVoks() < enteredNumberVoks)
		{
			JOptionPane.showMessageDialog(this,
					"Nicht ausreichend Vokabeln gespeichert");
		}
		wortLabel.setText(steuerung.liesAktuelleVokabel().liesWort());
	}

	private int frageVokabelAnzahl()
	{
		return Integer.parseInt(JOptionPane.showInputDialog(
				"Wie viele Vokabeln sollen abgefragt werden?", 30));
	}

	private void frageAb()
	{
		try
		{
			wortLabel.setText(steuerung.naechsteVokabel().liesWort());
		}
		catch (EndOfAbfrageException ignored)
		{
		}
	}

	private void checkVok()
	{
		if (steuerung.pruefeEingabe(uebersetzungField.getText()))
		{
			vokRichtig();
		}
		else
		{
			vokFalsch();
		}
	}

	public void vokFalsch()
	{
		JOptionPane.showMessageDialog(this,
				"Deine Eingabe ist leider falsch. Versuche es noch einmal");
	}

	public void vokRichtig()
	{
		JOptionPane.showMessageDialog(this,
				"Richtig! Auf zur nächsten Vokabel");
		frageAb();
	}
}
