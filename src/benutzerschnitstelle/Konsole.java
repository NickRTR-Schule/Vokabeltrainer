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
			System.out.println(vokabeln);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
