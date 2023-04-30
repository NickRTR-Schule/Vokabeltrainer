package fachkonzept.navigation;

import benutzerschnittstelle.navigation.UIScreens;
import steuerung.MainFrameSteuerung;

import java.util.ArrayList;

public final class NavigationStack
{

    private static final NavigationStack shared = new NavigationStack();
    private final ArrayList<NavigationStackItem> stack;

    private NavigationStack()
    {
        stack = new ArrayList<>();
        stack.add(new NavigationStackItem(UIScreens.Dashboard));
    }

    public static NavigationStack getInstance()
    {
        return shared;
    }

    private int lastIndex()
    {
        return stack.size() - 1;
    }

    public void forward(UIScreens screen)
    {
        stack.add(new NavigationStackItem(screen));
    }

    public void forward(UIScreens screen, Object obj)
    {
        stack.add(new NavigationStackItem(screen, obj));
    }

    public void back()
    {
        // TODO-js: implement
        final NavigationStackItem item = stack.get(lastIndex());
        MainFrameSteuerung.getInstance().open(item.getScreen(), item.getObject());
        stack.remove(lastIndex());
    }

    public void addToStack(NavigationStackItem item)
    {
        stack.add(item);
    }
}
