package benutzerschnittstelle.komponenten;

import javax.swing.*;

public class CustomPanel extends JPanel
{
    private final String title;


    public CustomPanel(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}
