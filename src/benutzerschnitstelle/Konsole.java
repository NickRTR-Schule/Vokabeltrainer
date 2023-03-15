package benutzerschnitstelle;

import java.util.ArrayList;

import datenspeicherung.Datenbank;
import datenspeicherung.Vokabel;

public class Konsole {

	public static void main(String[] args) {
		Datenbank derSpeicher = new Datenbank();
		
		try {
//			ArrayList<Vokabel> vokabeln = derSpeicher.liesVokabeln();
//			System.out.println(vokabeln);
			
//			Vokabel vokabel = derSpeicher.liesVokabel("cat", "Katze");
//			System.out.println(vokabel.liesUebersetzung());
			
//			derSpeicher.loescheVokabel("cat", "Katze");
			
			derSpeicher.vokabelHinzufuegen("duck", "Ente", null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
