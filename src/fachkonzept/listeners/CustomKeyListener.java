package fachkonzept.listeners;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Custom Key Listener to jump from one textfield to the next one
 */
public final class CustomKeyListener extends KeyAdapter
{

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            final Object src = e.getSource();
            if (src instanceof Component)
            {
                ((Component) src).transferFocus();
            }
        }
    }
}
