package benutzerschnittstelle.komponenten;

import datenspeicherung.Vokabel;
import steuerung.MainFrameSteuerung;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vokabeltile extends JPanel
{
    private final Vokabel vokabel;

    public Vokabeltile(Vokabel vok)
    {
        this.vokabel = vok;
        setValues();
        build();
    }

    /**
     * Sets all the Values that should be set for this component
     */
    private void setValues()
    {
        setName(vokabel.liesWort() + "Tile");
        setEnabled(true);
    }

    /**
     * Builds this Tile
     */
    private void build()
    {
        add(new JLabel(vokabel.liesWort()));
        add(new JLabel(vokabel.liesUebersetzung()));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // TODO-js: Work on that
                MainFrameSteuerung.getInstance().openErsteller();
            }
        });
    }
}
