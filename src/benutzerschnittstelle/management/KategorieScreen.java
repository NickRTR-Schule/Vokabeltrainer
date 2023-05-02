package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomButton;
import benutzerschnittstelle.komponenten.CustomPanel;
import benutzerschnittstelle.komponenten.CustomTextField;
import datenspeicherung.Kategorie;
import fachkonzept.listeners.CustomKeyListener;
import steuerung.MainFrameSteuerung;
import steuerung.management.KategorieScreenSteuerung;

import javax.swing.*;
import java.awt.*;

/**
 * The Screen showing all the Categories the User created to manage and organize
 * his / her Vocabs
 */
public final class KategorieScreen extends CustomPanel
{

    private final CustomTextField nameTxtField;

    /**
     * The Controller to this View
     */
    private final KategorieScreenSteuerung steuerung;

    public KategorieScreen()
    {
        super("Kategorie");
        nameTxtField = new CustomTextField();
        steuerung = new KategorieScreenSteuerung();
        setValues();
    }

    public KategorieScreen(Kategorie kategorie)
    {
        this();
        nameTxtField.setText(kategorie.liesName());
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
        panel.add(new JLabel("Name"), constraints);
        nameTxtField.addKeyListener(new CustomKeyListener());
        constraints.gridy = 1;
        panel.add(nameTxtField, constraints);
        constraints.gridy = 2;
        final CustomButton storeBtn = new CustomButton("Speichern");
        storeBtn.addActionListener((ignored) -> {
            if (nameTxtField.getText().length() > 0)
            {
                steuerung.kategorieHinzufuegen(nameTxtField.getText());
            }
            MainFrameSteuerung.getInstance().openDashboard();
        });
        panel.add(storeBtn, constraints);
        nameTxtField.requestFocus();
        return panel;
    }
}
