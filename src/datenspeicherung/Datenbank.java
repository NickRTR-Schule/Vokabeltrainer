package datenspeicherung;

import exceptions.datenbank.*;

import java.sql.*;
import java.util.ArrayList;

public final class Datenbank
{
    private static Connection con; // Verbindung zur DB
    private static PreparedStatement stmt; // DB-Anfrage (Query)
    private static ResultSet rs; // mögliches Ergebnis einer DB-Abfrage

    private static void oeffneDatenbank() throws DatenbankAccessException
    {
        try
        {
            // Verbindung zur Datenbank herstellen; Struktur:
            // jdbc = Java Database Connectivity = Mechanismus zum DB-Zugriff
            // ucanaccess =
            // C:/temp/usw. = Pfad zur Datenbank
            con = DriverManager
                    .getConnection("jdbc:ucanaccess://Vokabeltrainer.accdb"); // (Zu
            // Testzwecken)
        } catch (SQLException e)
        {
            throw new DatenbankAccessException(DatenbankAccessType.oeffnen);
        }
    }

    private static void schliesseDatenbank() throws DatenbankAccessException
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

    public static ArrayList<Vokabel> liesVokabeln()
            throws DatenbankAccessException, DatenbankLeseException
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
                        rs.getString("uebersetzung"), rs.getBytes("abbildung"),
                        rs.getBytes("aussprache"), rs.getString("lautschrift"),
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

    public static Vokabel liesVokabel(String wort, String uebersetzung)
            throws DatenbankAccessException, DatenbankLeseException
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
                        rs.getString("uebersetzung"), rs.getBytes("abbildung"),
                        rs.getBytes("aussprache"), rs.getString("lautschrift"),
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

    public static ArrayList<Vokabel> liesVokabelnForKat(String name)
            throws DatenbankAccessException, DatenbankLeseException
    {
        oeffneDatenbank();

        // DB-Abfrage als String
        String sqlStmt = "SELECT Vokabel.wort, Vokabel.uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis, wiederholungen, anzahlrichtig ";
        sqlStmt += "FROM Vokabel, Beziehung ";
        sqlStmt += "WHERE name = ?";

        ArrayList<Vokabel> ergebnis = null;

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (rs.next())
            {
                ergebnis = new ArrayList<>();
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                ergebnis.add(new Vokabel(rs.getString("wort"),
                        rs.getString("uebersetzung"), rs.getBytes("abbildung"),
                        rs.getBytes("aussprache"), rs.getString("lautschrift"),
                        rs.getString("verwendungshinweis"),
                        rs.getInt("wiederholungen"),
                        rs.getInt("anzahlrichtig")));
            }

            if (ergebnis == null)
            {
                throw new DatenbankLeseException(DatenbankObject.vokabel);
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }

        // schliesseDatenbank();
        return ergebnis;
    }

    private static boolean existiertVokabel(String wort, String uebersetzung)
    {
        // DB-Abfrage als String
        String sqlStmt = "SELECT * ";
        sqlStmt += "FROM Vokabel ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, wort);
            stmt.setString(2, uebersetzung);
            rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException ignored)
        {
            return false;
        }
    }

    public static void vokabelHinzufuegen(Vokabel dieVokabel)
            throws DatenbankAccessException, DatenbankSchreibException,
            DuplicateVokabelException, DatenbankLeseException
    {
        oeffneDatenbank();

        if (existiertVokabel(dieVokabel.liesWort(),
                dieVokabel.liesUebersetzung()))
        {
            throw new DuplicateVokabelException();
        }

        // DB-Abfrage als String
        String sqlStmt = "INSERT INTO vokabel (wort, uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis) ";
        sqlStmt += "VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, dieVokabel.liesWort());
            stmt.setString(2, dieVokabel.liesUebersetzung());
            stmt.setBytes(3, dieVokabel.liesAbbildung());
            stmt.setBytes(4, dieVokabel.liesAussprache());
            stmt.setString(5, dieVokabel.liesLautschrift());
            stmt.setString(6, dieVokabel.liesVerwendungshinweis());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new DatenbankSchreibException(DatenbankObject.vokabel);
        }

        schliesseDatenbank();
    }

    public static void loescheVokabel(String wort, String uebersetzung)
            throws DatenbankAccessException, DatenbankSchreibException
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

    public static ArrayList<Kategorie> liesKategorien()
            throws DatenbankAccessException, DatenbankLeseException
    {
        oeffneDatenbank();
        final ArrayList<Kategorie> ergebnis = new ArrayList<>();
        final ResultSet result;

        // DB-Abfrage als String
        String sqlStmt = "SELECT name ";
        sqlStmt += "FROM Kategorie ";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            result = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (result.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                final String name = result.getString("name");
                final ArrayList<Vokabel> vokabeln = new ArrayList<>(
                        liesVokabelnForKat(name));
                final Vokabel[] voks = new Vokabel[vokabeln.size()];
                ergebnis.add(new Kategorie(name, voks));
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.kategorie);
        }
        schliesseDatenbank();
        return ergebnis;
    }

    public static void loescheKategorie(String name)
            throws DatenbankAccessException, DatenbankSchreibException
    {
        oeffneDatenbank();

        // DB-Abfrage als String
        String sqlStmt = "DELETE FROM Kategorie ";
        sqlStmt += "WHERE name = ?";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new DatenbankSchreibException(DatenbankObject.kategorie);
        }

        schliesseDatenbank();
    }

    public static void vokabelWiederholt(Vokabel dieVokabel)
            throws DatenbankAccessException, DatenbankSchreibException
    {
        oeffneDatenbank();
        // DB-Abfrage als String
        String sqlStmt = "UPDATE vokabel ";
        sqlStmt += "SET wiederholungen = ?, anzahlrichtig = ? ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";
        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            stmt.setInt(1, dieVokabel.liesWiederholungen());
            stmt.setInt(2, dieVokabel.liesAnzahlRichtig());
            stmt.setString(3, dieVokabel.liesWort());
            stmt.setString(4, dieVokabel.liesUebersetzung());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new DatenbankSchreibException(DatenbankObject.vokabel);
        }
        schliesseDatenbank();
    }


    public static void vokabelZuKategorieHinzufuegen(Vokabel vok, Kategorie kat)
    {
        // TODO: implement
        }
    
    public static int wissensstand()
            throws DatenbankAccessException, DatenbankLeseException
    {
        int anzahlR = 0;
        int anzahl = 0;

        oeffneDatenbank();

        // DB-Abfrage als String
        String sqlStmt = "SELECT COUNT(*) AS anzahl ";
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
                anzahl = rs.getInt("anzahl");
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }

        // DB-Abfrage als String
        sqlStmt = "SELECT COUNT(*) AS anzahl ";
        sqlStmt += "FROM Vokabel ";
        sqlStmt += "WHERE anzahlrichtig >= 2";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            rs = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (rs.next())
            {
                anzahlR = rs.getInt("anzahl");
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }

        schliesseDatenbank();

        return Math.round(((float) anzahlR / anzahl) * 100);
    }
}
