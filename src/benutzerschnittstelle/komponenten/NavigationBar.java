package benutzerschnittstelle.komponenten;

import steuerung.MainFrameSteuerung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

/**
 * A Navigation Bar to use on the top of every screen to navigate back to the
 * Dashboard
 */
public final class NavigationBar extends JPanel
{

    private final String title;

    public NavigationBar(String title)
    {
        super(new FlowLayout(FlowLayout.LEFT));
        this.title = title;
        setValues();
        build();
    }

    private void setValues()
    {
        setName("Navigation Bar");
    }

    private void build()
    {
        final JButton btn = new JButton();
        btn.setIcon(iconLaden());
        btn.setText("Back");
        btn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                MainFrameSteuerung.getInstance().openDashboard();
            }
        });
        add(btn);
        add(new JLabel(title));
    }

    /**
     * Lädt ein bestimmtes Icon und gibt es als ImageIcon zurück. Als Name
     * reicht der Dateiname, wenn sie im Ordner assets liegen.
     *
     * @return das Icon
     */
    private ImageIcon iconLaden()
    {
        // Icon laden

        final InputStream stream = NavigationBar.class.getClassLoader()
                .getResourceAsStream("Icon_arrow_left_highres.png");
        final ImageIcon icon;

        try
        {
            assert stream != null;
            icon = new ImageIcon(stream.readAllBytes());
            final Image image = icon.getImage();
            final Image scaledInstance = image.getScaledInstance(25, 25,
                    Image.SCALE_DEFAULT);
            icon.setImage(scaledInstance);
            return icon;
        } catch (IOException e)
        {
            e.printStackTrace();
            return new ImageIcon();
        }
    }
}
