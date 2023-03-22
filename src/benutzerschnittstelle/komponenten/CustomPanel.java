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
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridheight = 1;
        add(new NavigationBar());
        constraints.gridy = 1;
        constraints.gridheight = 50;
        add(content);
    }
}
