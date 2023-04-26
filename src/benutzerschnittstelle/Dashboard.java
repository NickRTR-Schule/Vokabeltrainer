package benutzerschnittstelle;

import steuerung.DashboardSteuerung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        steuerung = new DashboardSteuerung(this);
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

    }
}
