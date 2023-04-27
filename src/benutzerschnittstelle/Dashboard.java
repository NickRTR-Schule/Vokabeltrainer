package benutzerschnittstelle;

import benutzerschnittstelle.komponenten.CustomButton;
import benutzerschnittstelle.komponenten.CustomPanel;
import steuerung.DashboardSteuerung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class Dashboard extends CustomPanel
{

    /**
     * The Controller to this View
     */
    private final DashboardSteuerung steuerung;

    /**
     * The Constraints being used in the Layout of this Panel
     */
    private final GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Create the frame.
     */
    public Dashboard()
    {
        super("Dashboard");
        steuerung = new DashboardSteuerung();
        setValues();
        build();
    }

    /**
     * Call all the Setter Methods to relevant variables in the init process of
     * this Frame
     */
    private void setValues()
    {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setMinimumSize(new Dimension(1000, 1000));
        setBackground(Color.WHITE);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setName(getTitle());
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(25, 25, 25, 25);
        final GridBagLayout layout = new GridBagLayout();
        layout.setConstraints(this, constraints);
        setLayout(layout);
    }

    /**
     * Adds the specified Component to the Panel, using the current State of the
     * Constraints.
     *
     * @param component the component that should be added
     */
    private void addComponent(Component component)
    {
        add(component, constraints);
    }

    /**
     * Part of the initialization Process, adding all the Components to this
     * Panel
     */
    private void build()
    {
        // Add Buttons
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridheight = 5;
        constraints.gridwidth = 10;
        final JPanel statsPanel = new JPanel();
        addComponent(statsPanel);
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.gridx = 1;
        final CustomButton vokabellisteBtn = new CustomButton(
                "Vokabelliste", "Zeige alle Vokabeln als Liste an");
        addComponent(vokabellisteBtn);
        constraints.gridx = 3;
        final CustomButton vokabelErstellerBtn = new CustomButton(
                "Vokabel erstellen", "Erstelle eine neue Vokabel");
        vokabelErstellerBtn.addActionListener((ignored) -> steuerung.erstellerGeklickt());
        addComponent(vokabelErstellerBtn);
        constraints.gridx = 5;
        final CustomButton kategorielisteBtn = new CustomButton(
                "Kategorieliste", "Zeige alle Kategorien in einer Liste an");
        kategorielisteBtn.addActionListener((ignored) -> steuerung.kategorielisteGeklickt());
        addComponent(kategorielisteBtn);
        constraints.gridx = 7;
        final CustomButton abfrageBtn = new CustomButton("Abfrage starten",
                "Starte eine Abfrage");
        abfrageBtn.addActionListener((ignored) -> steuerung.abfrageGeklickt());
        vokabellisteBtn.addActionListener((ignored) -> steuerung.vokabellisteGeklickt());
        addComponent(abfrageBtn);
    }
}
