package benutzerschnittstelle.error;

import benutzerschnittstelle.komponenten.CustomPanel;

import javax.swing.*;
import java.awt.*;

public final class ErrorScreen extends CustomPanel
{
    public ErrorScreen()
    {
        super("Error");
        setValues();
        build();
    }

    private void setValues()
    {
        setEnabled(true);
        setName(getTitle());
    }

    private void build()
    {
        setLayout(new GridLayout(10, 1));
        add(new JLabel("Error"));
        add(new JLabel("Something went wrong"));
        add(new JLabel("Please excuse the issue"));
    }
}
