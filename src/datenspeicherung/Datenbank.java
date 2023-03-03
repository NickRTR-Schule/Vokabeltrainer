package datenspeicherung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datenspeicherung.Vokabel;

public class Datenbank
{
	private Connection con; // Verbindung zur DB
	private PreparedStatement stmt; // DB-Anfrage (Query)
	private ResultSet rs; // mögliches Ergebnis einer DB-Abfrage

	public ArrayList<Vokabel> liesVokabeln() throws Exception {
		
	}
}
