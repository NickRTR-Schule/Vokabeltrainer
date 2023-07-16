package benutzerschnittstelle;

import benutzerschnittstelle.komponenten.CustomButton;
import benutzerschnittstelle.komponenten.CustomPanel;
import datenspeicherung.Datenbank;
import exceptions.datenbank.DatenbankAccessException;
import exceptions.datenbank.DatenbankLeseException;
import steuerung.DashboardSteuerung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        // statistics
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridheight = 3;
        constraints.gridwidth = 10;
        final JPanel statsPanel = new JPanel();
        statsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Statistiken");
        heading.setBorder(new EmptyBorder(0, 0, 5, 0)); // Adding a margin of 5 pixels below the label
        heading.setFont(new Font(MainFrame.liesFont().getFontName(), Font.BOLD, 18));
        statsPanel.add(heading);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.now();
        statsPanel.add(new JLabel("Uhrzeit: " + dtf.format(localTime)));

        try
        {
            statsPanel.add(new JLabel("Wissensstand: " + Datenbank.wissensstand() + " %"));
        } catch (DatenbankAccessException e)
        {
            throw new RuntimeException(e);
        } catch (DatenbankLeseException e)
        {
            throw new RuntimeException(e);
        }

        addComponent(statsPanel);

        constraints.gridy = 3;
        constraints.gridx = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        // Add Buttons

        final CustomButton vokabellisteBtn = new CustomButton("Vokabelliste",
                "Zeige alle Vokabeln als Liste an");
        vokabellisteBtn.addActionListener(
                (ignored) -> steuerung.vokabellisteGeklickt());
        addComponent(vokabellisteBtn);

        final CustomButton vokabelErstellerBtn = new CustomButton(
                "Vokabel erstellen", "Erstelle eine neue Vokabel");
        vokabelErstellerBtn
                .addActionListener((ignored) -> steuerung.erstellerGeklickt());
        constraints.gridx = 4;
        addComponent(vokabelErstellerBtn);

        final CustomButton kategorielisteBtn = new CustomButton(
                "Kategorieliste", "Zeige alle Kategorien in einer Liste an");
        kategorielisteBtn.addActionListener(
                (ignored) -> steuerung.kategorielisteGeklickt());
        constraints.gridx = 6;
        addComponent(kategorielisteBtn);

        final CustomButton abfrageBtn = new CustomButton("Abfrage starten",
                "Starte eine Abfrage");
        abfrageBtn.addActionListener((ignored) -> steuerung.abfrageGeklickt());
        constraints.gridy = 4;
        constraints.gridx = 1;
        constraints.gridheight = 2;
        constraints.gridwidth = 10;
        addComponent(abfrageBtn);
    }
}
