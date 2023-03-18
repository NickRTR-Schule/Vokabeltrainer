package benutzerschnittstelle;

import steuerung.DashboardSteuerung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public final class Dashboard extends JPanel
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
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setName("Dashboard");
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
        // Pane Content
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 7;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        final JScrollPane statsScrollPane = new JScrollPane(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        statsScrollPane.setWheelScrollingEnabled(true);
        addComponent(statsScrollPane);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        final JButton abfrageBtn = new JButton("Abfrage starten");
        abfrageBtn.setAction(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                steuerung.abfrageGeklickt();
            }
        });
        addComponent(abfrageBtn);
        constraints.gridx = 5;
        final JButton vokabelErstellerBtn = new JButton("Vokabel erstellen");
        vokabelErstellerBtn.addActionListener((ignored) -> {
            steuerung.erstellerGecklickt();
        });
        addComponent(vokabelErstellerBtn);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 7;
        final JPanel letzteKategorienPanel = new JPanel();
        letzteKategorienPanel.setBackground(Color.GRAY);
        letzteKategorienPanel.add(new JLabel("Letzte Kategorien"));
        addComponent(letzteKategorienPanel);
        constraints.gridy = 5;
        addComponent(letzteKategorienBuilder());
        constraints.gridy = 7;
        final JPanel alleKategorienPanel = new JPanel();
        alleKategorienPanel.setBackground(Color.GRAY);
        alleKategorienPanel.add(new JLabel("Alle Kategorien"));
        addComponent(alleKategorienPanel);
        constraints.gridy = 8;
        addComponent(alleKategorienBuilder());
    }

    /**
     * @return the Panel showing the last Kategorien
     */
    private JPanel letzteKategorienBuilder()
    {
        final JPanel panel = new JPanel();
        // for each loop over the last categories
        return panel;
    }

    /**
     * @return the Panel showing all Kategorien
     */
    private JPanel alleKategorienBuilder()
    {
        final JPanel panel = new JPanel();
        // For each loop over all categories
        return panel;
    }

}
