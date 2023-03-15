package datenspeicherung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Datenbank
{
	private Connection con; // Verbindung zur DB
	private PreparedStatement stmt; // DB-Anfrage (Query)
	private ResultSet rs; // mögliches Ergebnis einer DB-Abfrage

	public ArrayList<Vokabel> liesVokabeln() throws Exception {
		return new ArrayList<Vokabel>();
	}
}
