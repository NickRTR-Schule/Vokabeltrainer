package datenspeicherung;

import java.sql.*;
import java.util.ArrayList;

public final class Datenbank
{
    private Connection con; // Verbindung zur DB
    private PreparedStatement stmt; // DB-Anfrage (Query)
    private ResultSet rs; // mögliches Ergebnis einer DB-Abfrage

    private void oeffneDatenbank() throws Exception
    {
        try
        {
            // Verbindung zur Datenbank herstellen; Struktur:
            // jdbc = Java Database Connectivity = Mechanismus zum DB-Zugriff
            // ucanaccess =
            // C:/temp/usw. = Pfad zur Datenbank
            con = DriverManager.getConnection(
                    "jdbc:ucanaccess://Vokabeltrainer.accdb"); // (Zu Testzwecken)
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Öffnen der Datenbank: " + e.getLocalizedMessage());
        }
    }

    private void schliesseDatenbank() throws Exception
    {
        try
        {
            // Ergebnismenge schließen
            if (rs != null)
            {
                rs.close();
            }
            // Statement schließen
            stmt.close();
            // Verbindung schließen
            con.close();
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Schließen der Datenbank!");
        }
    }

    public ArrayList<Vokabel> liesVokabeln() throws Exception
    {
        oeffneDatenbank();
        ArrayList<Vokabel> ergebnis = new ArrayList<>();

        // DB-Abfrage als String
        String sqlStmt = "SELECT wort, uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis, wiederholungen, anzahlrichtig ";
        sqlStmt += "FROM Vokabel";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            rs = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (rs.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                ergebnis.add(new Vokabel(rs.getString("wort"),
                        rs.getString("uebersetzung"),
                        rs.getBytes("abbildung"),
                        rs.getBytes("aussprache"),
                        rs.getString("lautschrift"),
                        rs.getString("verwendungshinweis"),
                        rs.getInt("wiederholungen"),
                        rs.getInt("anzahlrichtig")));
            }
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Lesen der Vokabeln aus der Datenbank: " + e.getLocalizedMessage());
        }

        schliesseDatenbank();
        return ergebnis;
    }

    public Vokabel liesVokabel(String wort, String uebersetzung) throws Exception
    {
        oeffneDatenbank();

        // DB-Abfrage als String
        String sqlStmt = "SELECT wort, uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis, wiederholungen, anzahlrichtig ";
        sqlStmt += "FROM Vokabel ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";

        Vokabel ergebnis = null;

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, wort);
            stmt.setString(2, uebersetzung);
            rs = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (rs.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                ergebnis = new Vokabel(rs.getString("wort"),
                        rs.getString("uebersetzung"),
                        rs.getBytes("abbildung"),
                        rs.getBytes("aussprache"),
                        rs.getString("lautschrift"),
                        rs.getString("verwendungshinweis"),
                        rs.getInt("wiederholungen"),
                        rs.getInt("anzahlrichtig"));
            }

            if (ergebnis == null)
            {
                throw new Exception("Keine passende Vokabel gefunden.");
            }
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Lesen der Vokabel aus der Datenbank: " + e.getLocalizedMessage());
        }

        schliesseDatenbank();
        return ergebnis;
    }

    public void vokabelHinzufuegen(String wort, String uebersetzung, byte[] abbildung, byte[] aussprache, String lautschrift, String verwendungshinweis) throws Exception
    {
        oeffneDatenbank();

        // DB-Abfrage als String
        String sqlStmt = "INSERT INTO vokabel (wort, uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis) ";
        sqlStmt += "VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, wort);
            stmt.setString(2, uebersetzung);
            stmt.setBytes(3, abbildung);
            stmt.setBytes(4, aussprache);
            stmt.setString(5, lautschrift);
            stmt.setString(6, verwendungshinweis);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Speicher der Vokabeln in die Datenbank: " + e.getLocalizedMessage());
        }

        schliesseDatenbank();
    }

    public void loescheVokabel(String wort, String uebersetzung) throws Exception
    {
        oeffneDatenbank();

        // DB-Abfrage als String
        String sqlStmt = "DELETE FROM Vokabel ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, wort);
            stmt.setString(2, uebersetzung);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Löschen der Vokabel aus der Datenbank: " + e.getLocalizedMessage());
        }

        schliesseDatenbank();
    }

    // TODO: Vokabeln ändern

    public ArrayList<Kategorie> liesKategorien() throws Exception
    {
        oeffneDatenbank();
        ArrayList<Kategorie> ergebnis = new ArrayList<>();

        // DB-Abfrage als String
        String sqlStmt = "SELECT name, wort, uebersetzung";
        sqlStmt += "FROM Kategorie";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            rs = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (rs.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                final ArrayList<Vokabel> vokabeln = new ArrayList<>();
                vokabeln.add(liesVokabel(rs.getString("wort"), rs.getString("uebersetzung")));
                final Vokabel[] voks = new Vokabel[vokabeln.size()];
                ergebnis.add(new Kategorie(rs.getString("name"), voks));
            }
        } catch (SQLException e)
        {
            throw new Exception("Fehler beim Lesen der Vokabeln aus der Datenbank: " + e.getLocalizedMessage());
        }

        schliesseDatenbank();
        return ergebnis;
    }
}
