package benutzerschnittstelle.management;

import benutzerschnittstelle.komponenten.CustomPanel;

public class MappingView extends CustomPanel
{
    public MappingView()
    {
        super("Mapping Übersicht");
        setValues();
    }

    private void setValues()
    {
        setName(getTitle());
    }

    private void build()
    {

    }
}
