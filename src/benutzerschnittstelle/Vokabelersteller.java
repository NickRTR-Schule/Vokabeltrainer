package benutzerschnittstelle;

import steuerung.VokabelerstellerSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Screen to add / create a new Vocabulary.
 */
public final class Vokabelersteller extends JScrollPane
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
        steuerung = new VokabelerstellerSteuerung(this);
        wortTxtField = new JTextField();
        uebersetzungTxtField = new JTextField();
        lautschriftTxtField = new JTextField();
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
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        panel.add(new JLabel("Wort"));
        wortTxtField.addKeyListener(new CustomKeyListener());
        panel.add(wortTxtField);
        panel.add(new JLabel("Uebersetzung"));
        uebersetzungTxtField.addKeyListener(new CustomKeyListener());
        panel.add(uebersetzungTxtField);
        panel.add(btnPanel());
        panel.add(new JLabel("Lautschrift"));
        lautschriftTxtField.addKeyListener(new CustomKeyListener());
        panel.add(lautschriftTxtField);
        wortTxtField.requestFocus();
        return panel;
    }

    private JPanel btnPanel()
    {
        // TODO-js: Work on layout
        final JPanel panel = new JPanel();
        setBorder(BorderFactory.createLineBorder(Color.blue));
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
