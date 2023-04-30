package fachkonzept.navigation;

import benutzerschnittstelle.navigation.UIScreens;

public final class NavigationStackItem
{
    private final UIScreens screen;

    private final Object obj;

    public NavigationStackItem(UIScreens screen)
    {
        this.screen = screen;
        obj = null;
    }

    public NavigationStackItem(UIScreens screen, Object obj)
    {
        this.screen = screen;
        this.obj = obj;
    }

    public Object getObject()
    {
        return obj;
    }

    public UIScreens getScreen()
    {
        return screen;
    }
}
