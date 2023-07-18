package benutzerschnittstelle.komponenten;

import javax.swing.*;

public class CustomPanel extends JPanel
{
    private final String title;

    private final CustomButton btn;

    public CustomPanel(String title)
    {
        this.title = title;
        btn = null;
    }


    public CustomPanel(String title, CustomButton btn)
    {
        this.title = title;
        this.btn = btn;
    }


    public String getTitle()
    {
        return title;
    }

    public CustomButton getBtn()
    {
        return btn;
    }
}
