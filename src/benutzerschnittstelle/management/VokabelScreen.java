package benutzerschnittstelle.management;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import benutzerschnittstelle.komponenten.CustomButton;
import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.komponenten.CustomTextField;
import datenspeicherung.Vokabel;
import fachkonzept.listeners.CustomKeyListener;
import fachkonzept.navigation.NavigationStack;
import steuerung.management.VokabelScreenSteuerung;

/**
 * The Screen to add / create a new Vocabulary.
 */
public final class VokabelScreen extends CustomPanel
{

	/**
	 * The Controller to this View
	 */
	private final VokabelScreenSteuerung steuerung;

	// Text Fields
	private final CustomTextField wortTxtField;
	private final CustomTextField uebersetzungTxtField;
	private final CustomTextField lautschriftTxtField;

	private final CustomTextField verwendungsHinweisTxtField;

	private boolean bearbeiten;
	private Vokabel vokabel;

	public VokabelScreen()
	{
		super("Vokabel");
		bearbeiten = false;
		steuerung = new VokabelScreenSteuerung();
		wortTxtField = new CustomTextField();
		uebersetzungTxtField = new CustomTextField();
		lautschriftTxtField = new CustomTextField();
		verwendungsHinweisTxtField = new CustomTextField();
		setValues();
	}

	public VokabelScreen(Vokabel vokabel)
	{
		this();
		this.vokabel = vokabel;
		bearbeiten = true;
		wortTxtField.setText(vokabel.liesWort());
		uebersetzungTxtField.setText(vokabel.liesUebersetzung());
		lautschriftTxtField.setText(vokabel.liesLautschrift());
		verwendungsHinweisTxtField.setText(vokabel.liesVerwendungshinweis());
	}

	private void setValues()
	{
		final JScrollPane pane = new JScrollPane();
		pane.setBackground(Color.WHITE);
		pane.setLayout(new ScrollPaneLayout());
		pane.setViewportView(build());
		add(pane);
		setName(getName());
	}

	private JPanel build()
	{
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5, 5, 5, 5);
		final GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(this, constraints);
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(layout);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		panel.add(new JLabel("Wort"), constraints);
		wortTxtField.addKeyListener(new CustomKeyListener());
		constraints.gridy = 1;
		panel.add(wortTxtField, constraints);
		constraints.gridy = 2;
		panel.add(new JLabel("Uebersetzung"), constraints);
		uebersetzungTxtField.addKeyListener(new CustomKeyListener());
		constraints.gridy = 4;
		panel.add(uebersetzungTxtField, constraints);
		constraints.gridwidth = 1;
		constraints.gridy = 5;
		constraints.gridx = 1;
		final JButton abbildungsBtn = new JButton("Abbildung hinzufuegen");
		abbildungsBtn.addActionListener((ignored) -> {
		});
		panel.add(abbildungsBtn, constraints);
		constraints.gridx = 2;
		final JButton ausspracheBtn = new JButton("Aussprache hinzufuegen");
		ausspracheBtn.addActionListener((ignored) -> {
		});
		panel.add(ausspracheBtn, constraints);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		panel.add(new JLabel("Lautschrift"), constraints);
		lautschriftTxtField.addKeyListener(new CustomKeyListener());
		constraints.gridy = 7;
		panel.add(lautschriftTxtField, constraints);
		constraints.gridy = 8;
		panel.add(new JLabel("Verwendungshinweis"), constraints);
		constraints.gridy = 9;
		panel.add(verwendungsHinweisTxtField, constraints);
		constraints.gridy = 10;
		final CustomButton storeBtn = new CustomButton("Speichern");
		storeBtn.addActionListener((ignored) -> {
			if (wortTxtField.getText().length() > 0
					&& uebersetzungTxtField.getText().length() > 0)
			{
				if (!bearbeiten)
				{
					steuerung.vokabelHinzufuegen(new Vokabel(
							wortTxtField.getText(),
							uebersetzungTxtField.getText(), null, null,
							lautschriftTxtField.getText(),
							verwendungsHinweisTxtField.getText(), 0, 0));
				}
				else
				{
					steuerung.vokabelAendern(
							new Vokabel(wortTxtField.getText(),
									uebersetzungTxtField.getText(), null, null,
									lautschriftTxtField.getText(),
									verwendungsHinweisTxtField.getText(), 0, 0),
							vokabel);
				}
			}
			NavigationStack.getInstance().back();
		});
		panel.add(storeBtn, constraints);
		wortTxtField.requestFocus();
		return panel;
	}
}
