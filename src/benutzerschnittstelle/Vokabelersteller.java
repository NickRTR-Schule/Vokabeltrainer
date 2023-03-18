package benutzerschnittstelle;

import steuerung.VokabelerstellerSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Screen to add / create a new Vocabulary.
 */
@SuppressWarnings("serial")
public final class Vokabelersteller extends JPanel
{

    /**
     * The Controller to this View
     */
    private final VokabelerstellerSteuerung steuerung;

    // Text Fields
    private final JTextField wortTxtField;
    private final JTextField uebersetzungTxtField;
    private final JTextField lautschriftTxtField;

    public Vokabelersteller()
    {
        steuerung = new VokabelerstellerSteuerung();
        wortTxtField = new JTextField();
        uebersetzungTxtField = new JTextField();
        lautschriftTxtField = new JTextField();
        setValues();
        build();
    }

    private void setValues()
    {
        setLayout(new GridLayout(10, 1));
        setName("Vokabel Ersteller");
    }

    private void build()
    {
        add(new JLabel("Wort"));
        wortTxtField.addKeyListener(new CustomKeyListener());
        add(wortTxtField);
        add(new JLabel("Uebersetzung"));
        uebersetzungTxtField.addKeyListener(new CustomKeyListener());
        add(uebersetzungTxtField);
        add(btnPanel());
        lautschriftTxtField.addKeyListener(new CustomKeyListener());
        add(lautschriftTxtField);
    }

    private JPanel btnPanel()
    {
        // TODO-js: Work on layout
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        layout.setConstraints(this, constraints);
        panel.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        add(new JLabel("Abbildung"));
        final JButton abbildungsBtn = new JButton("Abbildung hinzufuegen");
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(abbildungsBtn);
        constraints.gridwidth = 3;
        constraints.gridy = 2;
        add(new JLabel("Aussprache"));
        final JButton ausspracheBtn = new JButton("Aussprache hinzufuegen");
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        add(ausspracheBtn);
        return panel;
    }
}

/**
 * Custom Key Listener to
 * jump from one textfield to the
 * next one
 */
final class CustomKeyListener implements KeyListener
{

    @Override
    public void keyTyped(KeyEvent e)
    {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.isActionKey())
        {
            // Next Text Field request Focus
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // Do nothing
    }
}
