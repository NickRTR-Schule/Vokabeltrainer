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
import steuerung.MainFrameSteuerung;

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

	private int enteredNumberVoks;

	private int anzahlAbfragen = 0;
	private int anzahlRichtig = 0;

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
			enteredNumberVoks = steuerung.anzahlVoks();
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
			double erfolgsquote = (double) anzahlRichtig / anzahlAbfragen * 100;
			erfolgsquote = Math.round(erfolgsquote * 100) / 100;
			JOptionPane.showMessageDialog(this,
					"Anzahl Vokabeln gelernt: " + enteredNumberVoks
							+ "\nAnzahl Abfragen: " + anzahlAbfragen
							+ "\nDavon richtig: " + anzahlRichtig
							+ "\nErfolgsquote: " + erfolgsquote + "%");
			MainFrameSteuerung.getInstance().openDashboard();
		}
	}

	private void checkVok()
	{
		anzahlAbfragen++;
		try
		{
			boolean richtig = steuerung
					.pruefeEingabe(uebersetzungField.getText());
			if (richtig)
			{
				anzahlRichtig++;
				vokRichtig();
				uebersetzungField.setText("");
			}
			else
			{
				vokFalsch();
				uebersetzungField.selectAll();
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void vokFalsch()
	{
		JOptionPane.showMessageDialog(this,
				"Deine Eingabe ist leider falsch. Versuche es noch einmal");
	}

	public void vokRichtig()
	{
		// JOptionPane.showMessageDialog(this,
		// "Richtig! Auf zur nächsten Vokabel");
		frageAb();
	}
}
