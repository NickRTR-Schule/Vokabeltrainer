package benutzerschnitstelle;

import java.util.ArrayList;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

public class Konsole {

	public static void main(String[] args) {
		Datenbank derSpeicher = new Datenbank();
		
		ArrayList<Vokabel> vokabeln;
		try {
			vokabeln = derSpeicher.liesVokabeln();
			
			Vokabel vokabel = derSpeicher.liesVokabel("siue", "siu");
			
			System.out.println(vokabel.liesUebersetzung());
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getLocalizedMessage());
		}
	}

}
