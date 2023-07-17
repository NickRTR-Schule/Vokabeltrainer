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

    private final GridBagLayout layout;
    private final GridBagConstraints constraints;
    private final O object;
    private ListView<?> listView;

    public MappingWindow(O object, ArrayList<T> objects)
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
        this.object = object;
        this.objects = objects;
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
        setLayout(layout);
    }

    private void build()
    {
        init();
        layout.setConstraints(this, constraints);
        addComponent(new JLabel("kurze Beschreibung"));
        if (type == MappingType.vok)
        {
            listView = new Kategorieliste((Vokabel) object, (ArrayList<Kategorie>) objects);
        } else if (type == MappingType.kat)
        {
            listView = new Vokabelliste((Kategorie) object, (ArrayList<Vokabel>) objects);
        } else
        {
            // Abort execution
            return;
        }
        constraints.gridx = 0;
        constraints.gridy = 0;
        addComponent(listView);
        constraints.gridy = 1;
        final CustomButton saveBtn = new CustomButton("Speichern", "Speichere deine Änderungen");
        saveBtn.addActionListener((ignored) -> {
            dispose();
        });
        addComponent(saveBtn);
    }

    private void addComponent(Component comp)
    {
        add(comp, constraints);
    }

    private enum MappingType
    {
        vok,
        kat,
        none,
    }
}
