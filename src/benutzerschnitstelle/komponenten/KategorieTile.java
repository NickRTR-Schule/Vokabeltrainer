package benutzerschnitstelle.komponenten;

import datenspeicherung.Kategorie;

import javax.swing.*;

/**
 * A Tile representing a
 * single Kategorie, for Example
 * in the Dashboard Kategorie Section
 */
@SuppressWarnings("serial")
public final class KategorieTile extends JPanel {

    /**
     * The Kategorie this Tile
     * represents
     */
    private final Kategorie kategorie;

    public KategorieTile(Kategorie kategorie) {
        this.kategorie = kategorie;
        setValues();
        build();
    }

    /**
     * Sets all the Values that
     * should be set for this component
     */
    private void setValues() {
        setName(kategorie.liesName() + "Tile");
        setEnabled(true);
    }

    /**
     * Builds this Tile
     */
    private void build() {
        add(new JLabel(kategorie.liesName()));
    }
}
