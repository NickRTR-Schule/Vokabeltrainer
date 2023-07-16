package benutzerschnittstelle.komponenten;

import benutzerschnittstelle.management.Kategorieliste;
import benutzerschnittstelle.management.ListView;
import benutzerschnittstelle.management.Vokabelliste;
import datenspeicherung.Datenbank;
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

    private ListView<?> listView;

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
        constraints.gridx = 0;
        constraints.gridy = 0;
        addComponent(listView);
        constraints.gridy = 1;
        final CustomButton saveBtn = new CustomButton("Speichern", "Speichere deine Änderungen");
        saveBtn.addActionListener((ignored) -> {
            save();
        });
        addComponent(saveBtn);
    }

    private void addComponent(Component comp)
    {
        add(comp, constraints);
    }

    private void save()
    {
        if (type == MappingType.vok)
        {
            final ArrayList<Kategorie> kats = (ArrayList<Kategorie>) listView.getSelectedObjects();
            final Vokabel vok = (Vokabel) object;
            try
            {
                for (Kategorie kat : Datenbank.liesKategorien())
                {
                    if (kats.contains(kat))
                    {
                        kat.fuegeVokabelHinzu(vok);
                    } else
                    {
                        kat.entferneVokabel(vok);
                    }
                }
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Fehler beim Speichern");
                return;
            }
        } else if (type == MappingType.kat)
        {
            final Kategorie kat = (Kategorie) object;
            kat.aendereVokabeln((ArrayList<Vokabel>) listView.getSelectedObjects());
        }
        listView.getSelectedObjects();
    }

    private enum MappingType
    {
        vok,
        kat,
        none,
    }
}
