package datenspeicherung;

import exceptions.datenbank.*;

import java.sql.*;
import java.util.ArrayList;

public final class Datenbank
{
    private static Connection con; // Verbindung zur DB
    private static PreparedStatement stmt; // DB-Anfrage (Query)

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
            final ResultSet result = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (result.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                ergebnis.add(new Vokabel(result.getString("wort"),
                        result.getString("uebersetzung"), result.getBytes("abbildung"),
                        result.getBytes("aussprache"), result.getString("lautschrift"),
                        result.getString("verwendungshinweis"),
                        result.getInt("wiederholungen"),
                        result.getInt("anzahlrichtig"),
                        liesKategorienFuerVokabel(result.getString("wort"), result.getString("uebersetzung"))
                ));
            }
            result.close();
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
            final ResultSet result = stmt.executeQuery();
            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (result.next())
            {
                ergebnis = new ArrayList<>();
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                ergebnis.add(new Vokabel(result.getString("wort"),
                        result.getString("uebersetzung"), result.getBytes("abbildung"),
                        result.getBytes("aussprache"), result.getString("lautschrift"),
                        result.getString("verwendungshinweis"),
                        result.getInt("wiederholungen"),
                        result.getInt("anzahlrichtig"),
                        liesKategorienFuerVokabel(result.getString("wort"), result.getString("uebersetzung"))
                ));
            }
            result.close();
            if (ergebnis == null)
            {
                throw new DatenbankLeseException(DatenbankObject.vokabel);
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }
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
            final ResultSet result = stmt.executeQuery();
            final boolean r = result.next();
            result.close();
            return r;
        } catch (SQLException ignored)
        {
            return false;
        }
    }

    public static void vokabelHinzufuegen(Vokabel dieVokabel)
            throws DatenbankAccessException, DatenbankSchreibException,
            DuplicateVokabelException
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

    public static ArrayList<Kategorie> liesKategorien()
            throws DatenbankAccessException, DatenbankLeseException
    {
        oeffneDatenbank();
        final ArrayList<Kategorie> ergebnis = new ArrayList<>();
        // DB-Abfrage als String
        String sqlStmt = "SELECT name ";
        sqlStmt += "FROM Kategorie ";
        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            // DB-Abfrage ausführen
            final ResultSet result = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (result.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                final String name = result.getString("name");
                final ArrayList<Vokabel> vokabeln = new ArrayList<>(
                        liesVokabelnForKat(name));
                ergebnis.add(new Kategorie(name, vokabeln));
            }
            result.close();
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.kategorie);
        }
        schliesseDatenbank();
        return ergebnis;
    }

    // TODO: Vokabeln ändern
    // TODO: checken ob Vokabel schon existiert

    public static void loescheKategorie(String name)
            throws DatenbankAccessException, DatenbankSchreibException
    {
        oeffneDatenbank();
        String sqlStmt = "DELETE FROM Kategorie ";
        sqlStmt += "WHERE name = ?";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
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
        String sqlStmt = "UPDATE vokabel ";
        sqlStmt += "SET wiederholungen = ?, anzahlrichtig = ? ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
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
        String sqlStmt = "SELECT COUNT(*) AS anzahl ";
        sqlStmt += "FROM Vokabel";
        ResultSet result;
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            result = stmt.executeQuery();
            while (result.next())
            {
                anzahl = result.getInt("anzahl");
            }
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }
        sqlStmt = "SELECT COUNT(*) AS anzahl ";
        sqlStmt += "FROM Vokabel ";
        sqlStmt += "WHERE anzahlrichtig >= 2";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            result = stmt.executeQuery();
            while (result.next())
            {
                anzahlR = result.getInt("anzahl");
            }
            result.close();
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }
        schliesseDatenbank();
        return Math.round(((float) anzahlR / anzahl) * 100);
    }

    private static ArrayList<Kategorie> liesKategorienFuerVokabel(String wort, String uebersetzung) throws DatenbankAccessException, DatenbankLeseException
    {
        final ArrayList<Kategorie> ergebnis = new ArrayList<>();
        // DB-Abfrage als String
        String sqlStmt = "SELECT name ";
        sqlStmt += "FROM Kategorie, Beziehung ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";

        try
        {
            // DB-Abfrage vorbereiten
            stmt = con.prepareStatement(sqlStmt);
            stmt.setString(1, wort);
            stmt.setString(2, uebersetzung);

            // DB-Abfrage ausführen

            final ResultSet result = stmt.executeQuery();

            // Ergebnis der DB-Abfrage Zeile für Zeile abarbeiten
            while (result.next())
            {
                // DB-Zeile als Objekt in Ergebnis-Array speichern
                final String name = result.getString("name");
                final ArrayList<Vokabel> vokabeln = new ArrayList<>(
                        liesVokabelnForKat(name));
                ergebnis.add(new Kategorie(name, vokabeln));
            }
            result.close();
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.kategorie);
        }
        return ergebnis;
    }
}
