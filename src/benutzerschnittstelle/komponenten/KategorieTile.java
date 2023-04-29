package benutzerschnittstelle.komponenten;

import datenspeicherung.Kategorie;
import steuerung.MainFrameSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A Tile representing a single Kategorie, for Example in the Dashboard
 * Kategorie Section
 */
public final class KategorieTile extends JPanel
{

    /**
     * The Kategorie this Tile represents
     */
    private final Kategorie kategorie;

    public KategorieTile(Kategorie kategorie)
    {
        this.kategorie = kategorie;
        setValues();
        build();
    }

    /**
     * Sets all the Values that should be set for this component
     */
    private void setValues()
    {
        setName(kategorie.liesName() + "Tile");
        setEnabled(true);
        setBackground(Color.WHITE);
    }

    /**
     * Builds this Tile
     */
    private void build()
    {
        add(new JLabel(kategorie.liesName()));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                MainFrameSteuerung.getInstance()
                        .openKategorieuebersicht(kategorie);
            }
        });
        add(new JLabel(kategorie.liesVokabeln().length + "Vokabeln"));
    }
}
