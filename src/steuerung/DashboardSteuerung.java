package steuerung;

public class DashboardSteuerung {

    public void abfrageGeklickt() {
        MainFrameSteuerung.getInstance().openAbfrage();
    }

    public void erstellerGecklickt() {
        MainFrameSteuerung.getInstance().openErsteller();
    }

}
