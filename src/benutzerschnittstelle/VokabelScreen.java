package benutzerschnittstelle;

import benutzerschnittstelle.komponenten.CustomButton;
import datenspeicherung.Vokabel;
import steuerung.MainFrameSteuerung;
import steuerung.VokabelScreenSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Screen to add / create a new Vocabulary.
 */
public final class VokabelScreen extends JScrollPane
{

    /**
     * The Controller to this View
     */
    private final VokabelScreenSteuerung steuerung;

    // Text Fields
    private final JTextField wortTxtField;
    private final JTextField uebersetzungTxtField;
    private final JTextField lautschriftTxtField;

    private final JTextField verwendungsHinweisTxtField;

    public VokabelScreen()
    {
        steuerung = new VokabelScreenSteuerung();
        wortTxtField = new JTextField();
        uebersetzungTxtField = new JTextField();
        lautschriftTxtField = new JTextField();
        verwendungsHinweisTxtField = new JTextField();
        setValues();
    }

    public VokabelScreen(Vokabel vokabel)
    {
        this();
        wortTxtField.setText(vokabel.liesWort());
        uebersetzungTxtField.setText(vokabel.liesUebersetzung());
        lautschriftTxtField.setText(vokabel.liesLautschrift());
        verwendungsHinweisTxtField.setText(vokabel.liesVerwendungshinweis());
        setValues();
    }

    private void setValues()
    {
        setLayout(new ScrollPaneLayout());
        setViewportView(build());
        setName("Vokabel Ersteller");
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
                steuerung.vokabelHinzufuegen(wortTxtField.getText(),
                        uebersetzungTxtField.getText(), null, null,
                        lautschriftTxtField.getText(),
                        verwendungsHinweisTxtField.getText());
            }
            MainFrameSteuerung.getInstance().openDashboard();
        });
        panel.add(storeBtn, constraints);
        wortTxtField.requestFocus();
        return panel;
    }
}

/**
 * Custom Key Listener to jump from one textfield to the next one
 */
final class CustomKeyListener extends KeyAdapter
{

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            final Object src = e.getSource();
            if (src instanceof Component)
            {
                ((Component) src).transferFocus();
            }
        }
    }
}
