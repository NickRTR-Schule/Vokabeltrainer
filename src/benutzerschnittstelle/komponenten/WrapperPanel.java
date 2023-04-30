package benutzerschnittstelle.komponenten;

import benutzerschnittstelle.navigation.NavigationBar;

import javax.swing.*;
import java.awt.*;

public final class WrapperPanel extends JPanel
{

    private final GridBagConstraints constraints;

    public WrapperPanel(CustomPanel content)
    {
        constraints = new GridBagConstraints();
        setBackground(Color.WHITE);
        build(content);
    }

    private void build(CustomPanel content)
    {
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new NavigationBar(content.getTitle()), constraints);
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        add(content, constraints);
    }
}
