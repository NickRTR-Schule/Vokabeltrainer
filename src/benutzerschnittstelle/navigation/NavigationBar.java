package benutzerschnittstelle.navigation;

import benutzerschnittstelle.komponenten.CustomButton;
import fachkonzept.datamangement.converting.CustomConverter;
import fachkonzept.navigation.NavigationStack;

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

    private final CustomButton btn;

    private final GridBagConstraints constraints;

    public NavigationBar(String title, CustomButton btn)
    {
        super(new FlowLayout(FlowLayout.LEFT));
        this.title = title;
        this.btn = btn;
        constraints = new GridBagConstraints();
        setValues();
        build();
    }

    private void setValues()
    {
        setName("Navigation Bar");
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 2;
        constraints.insets = new Insets(5, 10, 5, 10);
        final GridBagLayout layout = new GridBagLayout();
        layout.setConstraints(this, constraints);
        setLayout(layout);
    }

    private void build()
    {
        constraints.gridx = 0;
        constraints.gridy = 0;
        final JButton btn = new JButton();
        btn.setIcon(iconLaden());
        btn.setText("Zurück");
        btn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                NavigationStack.getInstance().back();
            }
        });
        add(btn, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 10;
        final JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, constraints);
        if (this.btn != null)
        {
            constraints.gridwidth = 1;
            constraints.gridx = 12;
            add(this.btn, constraints);
        }
    }

    /**
     * Lädt ein bestimmtes Icon und gibt es als ImageIcon zurück. Als Name
     * reicht der Dateiname, wenn sie im Ordner assets liegen.
     *
     * @return das Icon
     */
    private ImageIcon iconLaden()
    {
        try (final InputStream stream = NavigationBar.class.getClassLoader()
                .getResourceAsStream("Icon_arrow_left_highres.png"))
        {
            try
            {
                assert stream != null;
                return CustomConverter.byteToIcon(stream.readAllBytes(), 25);
            } catch (IOException e)
            {
                e.printStackTrace();
                return new ImageIcon();
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
