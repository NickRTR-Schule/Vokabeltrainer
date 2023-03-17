package steuerung;

public final class DashboardSteuerung {

    public void abfrageGeklickt() {
        MainFrameSteuerung.getInstance().openAbfrage();
    }

    public void erstellerGecklickt() {
        MainFrameSteuerung.getInstance().openErsteller();
    }

}
