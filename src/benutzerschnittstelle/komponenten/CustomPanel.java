package benutzerschnittstelle.komponenten;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel
{

    private final GridBagConstraints constraints;

    public CustomPanel(Component content)
    {
        constraints = new GridBagConstraints();
        build(content);
    }

    private void build(Component content)
    {
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new NavigationBar(), constraints);
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        add(content, constraints);
    }
}
