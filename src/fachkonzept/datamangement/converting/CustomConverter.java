package fachkonzept.datamangement.converting;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CustomConverter
{

    public static ImageIcon byteToIcon(byte[] bytes, int scale) throws IOException
    {
        final BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(bytes));
        final ImageIcon icon = new ImageIcon(bfi);
        final Image image = icon.getImage();
        final Image scaledInstance = image.getScaledInstance(scale, scale, Image.SCALE_DEFAULT);
        icon.setImage(scaledInstance);
        return icon;
    }
}
