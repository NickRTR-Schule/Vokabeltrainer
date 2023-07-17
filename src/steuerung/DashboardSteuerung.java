package steuerung;

public final class DashboardSteuerung
{

    public void abfrageGeklickt()
    {
        MainFrameSteuerung.getInstance().openAbfrage();
    }

    public void erstellerGeklickt()
    {
        MainFrameSteuerung.getInstance().openErsteller();
    }

    public void vokabellisteGeklickt()
    {
        MainFrameSteuerung.getInstance().openVokabelliste();
    }

    public void kategorielisteGeklickt()
    {
        MainFrameSteuerung.getInstance().openKategorieliste();
    }

    public void statsGeklickt()
    {
        MainFrameSteuerung.getInstance().openStats();
    }
}
