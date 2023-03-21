package datenspeicherung;

import exceptions.datenbank.*;

import java.sql.*;
import java.util.ArrayList;

public final class Datenbank
{
    private Connection con; // Verbindung zur DB
    private PreparedStatement stmt; // DB-Anfrage (Query)
    private ResultSet rs; // mögliches Ergebnis einer DB-Abfrage

    private void oeffneDatenbank() throws DatenbankAccessException
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
            throw new DatenbankAccessException(DatenbankAccessType.oeffnen);
        }
    }

    private void schliesseDatenbank() throws DatenbankAccessException
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
            throw new DatenbankAccessException(DatenbankAccessType.schliessen);
        }
    }

    public ArrayList<Vokabel> liesVokabeln() throws DatenbankAccessException, DatenbankLeseException
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
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }

        schliesseDatenbank();
        return ergebnis;
    }

    public Vokabel liesVokabel(String wort, String uebersetzung) throws DatenbankAccessException, DatenbankLeseException
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
                throw new DatenbankLeseException(DatenbankObject.vokabel);
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }

        schliesseDatenbank();
        return ergebnis;
    }

    public void vokabelHinzufuegen(String wort, String uebersetzung, byte[] abbildung, byte[] aussprache, String lautschrift, String verwendungshinweis) throws DatenbankAccessException, DatenbankSchreibException, DuplicateVokabelException
    {
        oeffneDatenbank();
        // TODO-nick: Pruefe ob es die Vokabel schon gibt und throw DuplicateVokabelException

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
            throw new DatenbankSchreibException(DatenbankObject.vokabel);
        }

        schliesseDatenbank();
    }

    public void loescheVokabel(String wort, String uebersetzung) throws DatenbankAccessException, DatenbankSchreibException
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
            throw new DatenbankSchreibException(DatenbankObject.vokabel);
        }

        schliesseDatenbank();
    }

    // TODO: Vokabeln ändern
    // TODO: checken ob Vokabel schon existiert

    public ArrayList<Kategorie> liesKategorien() throws DatenbankAccessException, DatenbankLeseException
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
            throw new DatenbankLeseException(DatenbankObject.kategorie);
        }

        schliesseDatenbank();
        return ergebnis;
    }
}
