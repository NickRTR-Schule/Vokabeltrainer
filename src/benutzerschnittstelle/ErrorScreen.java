package benutzerschnittstelle;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public final class ErrorScreen extends JPanel
{
    public ErrorScreen()
    {
        setValues();
        build();
    }

    private void setValues()
    {
        setEnabled(true);
        setName("Error Screen");
    }

    private void build()
    {
        setLayout(new GridLayout(10, 1));
        add(new JLabel("Error"));
        add(new JLabel("Something went wrong"));
        add(new JLabel("Please excuse the issue"));
    }
}
