package benutzerschnittstelle.komponenten;

import steuerung.MainFrameSteuerung;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * A Navigation Bar
 * to use on the top
 * of every screen to navigate back
 * to the Dashboard
 */
public class NavigationBar extends JPanel
{

    public NavigationBar()
    {
        super(new FlowLayout(FlowLayout.LEFT));
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
        setBackground(Color.yellow);
        add(btn);
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
                .getResourceAsStream("KategorieArrow.png");
        final ImageIcon icon;

        try
        {
            assert stream != null;
            icon = rotate(ImageIO.read(stream));
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

    // Refer to this discussion: https://stackoverflow.com/questions/50883802/how-to-rotate-an-imageicon-in-java
    // Code from: https://stackoverflow.com/a/50884149/16376071
    public ImageIcon rotate(BufferedImage image)
    {
        // TODO: change to rotated image
        // Calculate the new size of the image based on the angle of rotaion
        final double radians = Math.toRadians(90);
        final double sin = Math.abs(Math.sin(radians));
        final double cos = Math.abs(Math.cos(radians));
        final int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
        final int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

        // Create a new image
        final BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = rotate.createGraphics();
        // Calculate the "anchor" point around which the image will be rotated
        final int x = (newWidth - image.getWidth()) / 2;
        final int y = (newHeight - image.getHeight()) / 2;
        // Transform the origin point around the anchor point
        final AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        // Paint the originl image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return new ImageIcon(rotate);
    }

}
