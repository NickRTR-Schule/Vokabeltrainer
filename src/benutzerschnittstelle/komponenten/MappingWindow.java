package benutzerschnittstelle.komponenten;

import benutzerschnittstelle.management.Kategorieliste;
import benutzerschnittstelle.management.ListView;
import benutzerschnittstelle.management.Vokabelliste;
import datenspeicherung.Kategorie;
import datenspeicherung.Vokabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MappingWindow<O, T> extends JFrame
{

    /*
    The List of Objects to map to
    provided single Object.
    This has another Type than the provided Objects,
    because you always map to different Objects.
     */
    private final ArrayList<T> objects;

    private final MappingType type;

    /*
    The Object to map other objects to.
    This is a single instance such as a vocabulary
    or a category
     */
    private final O object;
    private final GridBagLayout layout;
    private final GridBagConstraints constraints;

    public MappingWindow(O object)
    {
        if (object instanceof Vokabel)
        {
            type = MappingType.vok;
        } else if (object instanceof Kategorie)
        {
            type = MappingType.kat;
        } else
        {
            type = MappingType.none;
        }
        objects = new ArrayList<T>();
        this.object = object;
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        build();
    }

    private void init()
    {
        setTitle("Mapping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(false);
        setEnabled(true);
        setFocusable(true);
        setName("Mapping Frame");
    }

    private void build()
    {
        init();
        layout.setConstraints(this, constraints);
        addComponent(new JLabel("kurze Beschreibung"));
        final ListView<?> listView;
        if (type == MappingType.vok)
        {
            listView = new Kategorieliste((Vokabel) object);
        } else if (type == MappingType.kat)
        {
            listView = new Vokabelliste((Kategorie) object);
        } else
        {
            // Abort execution
            return;
        }
        addComponent(listView);
    }

    private void addComponent(Component comp)
    {
        add(comp, constraints);
    }

    private void mapObject(T object)
    {

    }

    private enum MappingType
    {
        vok,
        kat,
        none,
    }
}
