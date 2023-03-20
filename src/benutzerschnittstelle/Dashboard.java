package benutzerschnittstelle;

import benutzerschnittstelle.komponenten.KategorieTile;
import benutzerschnittstelle.komponenten.RoundedBorder;
import benutzerschnittstelle.komponenten.StatistikPanel;
import datenspeicherung.Kategorie;
import steuerung.DashboardSteuerung;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public final class Dashboard extends JPanel
{

    /**
     * The Controller to this View
     */
    private final DashboardSteuerung steuerung;

    /**
     * The Constraints being used in the Layout of this Panel
     */
    private final GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Create the frame.
     */
    public Dashboard()
    {
        steuerung = new DashboardSteuerung();
        setValues();
        build();
    }

    /**
     * Call all the Setter Methods to relevant variables in the init process of
     * this Frame
     */
    private void setValues()
    {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setMinimumSize(new Dimension(1000, 1000));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setName("Dashboard");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(25, 25, 25, 25);
        final GridBagLayout layout = new GridBagLayout();
        layout.setConstraints(this, constraints);
        setLayout(layout);
    }

    /**
     * Adds the specified Component to the Panel, using the current State of the
     * Constraints.
     *
     * @param component the component that should be added
     */
    private void addComponent(Component component)
    {
        add(component, constraints);
    }

    /**
     * Part of the initialization Process, adding all the Components to this
     * Panel
     */
    private void build()
    {
        // Pane Content
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 7;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        addComponent(createStats());
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        final JButton abfrageBtn = new JButton("Abfrage starten");
        abfrageBtn.addActionListener((ignored) -> {
            steuerung.abfrageGeklickt();
        });
        addComponent(abfrageBtn);
        constraints.gridx = 5;
        final JButton vokabelErstellerBtn = new JButton("Vokabel erstellen");
        vokabelErstellerBtn.addActionListener((ignored) -> {
            steuerung.erstellerGeklickt();
        });
        addComponent(vokabelErstellerBtn);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 7;
        final JPanel letzteKategorienPanel = new JPanel();
        letzteKategorienPanel.setBackground(Color.GRAY);
        letzteKategorienPanel.add(new JLabel("Letzte Kategorien"));
        addComponent(letzteKategorienPanel);
        constraints.gridy = 5;
        addComponent(letzteKategorienBuilder());
        constraints.gridy = 7;
        final JPanel alleKategorienPanel = new JPanel();
        alleKategorienPanel.setBackground(Color.GRAY);
        alleKategorienPanel.add(new JLabel("Alle Kategorien"));
        addComponent(alleKategorienPanel);
        constraints.gridy = 8;
        addComponent(alleKategorienBuilder());
    }

    /**
     * Creates the Statistik Panel
     *
     * @return a JPanel with the Stats as Content
     */
    private JPanel createStats()
    {
        final JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 3));
        final StatistikPanel stats1 = new StatistikPanel();
        stats1.setBorder(new BevelBorder(BevelBorder.RAISED));
        stats1.add(new JLabel("Stats 1 Text"));
        stats1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                steuerung.statsGeklickt();
            }
        });
        statsPanel.add(stats1);
        final StatistikPanel stats2 = new StatistikPanel();
        stats2.setBorder(new RoundedBorder(25));
        stats2.add(new JLabel("Stats 2 Text"));
        stats2.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                steuerung.statsGeklickt();
            }
        });
        statsPanel.add(stats2);
        final StatistikPanel stats3 = new StatistikPanel();
        stats3.setBorder(new BevelBorder(BevelBorder.RAISED));
        stats3.add(new JLabel("Stats 3 Text"));
        stats3.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                steuerung.statsGeklickt();
            }
        });
        statsPanel.add(stats3);
        return statsPanel;
    }

    /**
     * @return the Panel showing the last Kategorien
     * last meaning the last 3
     */
    private JPanel letzteKategorienBuilder()
    {
        final JPanel panel = new JPanel();
        // TODO-js: set Layout
        final ArrayList<Kategorie> kats = steuerung.liesKategorien();
        for (int i = 0; i < Math.min(kats.size(), 3); i++)
        {
            panel.add(new KategorieTile(steuerung.liesKategorien().get(i)));
        }
        return panel;
    }

    /**
     * @return the Panel showing all Kategorien
     */
    private JPanel alleKategorienBuilder()
    {
        final JPanel panel = new JPanel();
        // TODO-js: set Layout
        for (Kategorie kat : steuerung.liesKategorien())
        {
            panel.add(new KategorieTile(kat));
        }
        return panel;
    }

}
