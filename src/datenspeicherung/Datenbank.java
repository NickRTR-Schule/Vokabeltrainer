package datenspeicherung;

import exceptions.datenbank.*;

import java.sql.*;
import java.util.ArrayList;

public final class Datenbank
{
    private static Connection con; // Verbindung zur DB
    private static PreparedStatement stmt; // DB-Anfrage (Query)

    private static ArrayList<Vokabel> vokabeln;
    private static ArrayList<Kategorie> kategorien;

    public static ArrayList<Vokabel> liesVokabeln()
    {
        return vokabeln;
    }

    public static ArrayList<Kategorie> liesKategorien()
    {
        return kategorien;
    }

    /**
     * Call this Method at the start of the Program
     */
    public static void init() throws DatenbankAccessException, DatenbankLeseException
    {
        ladeDaten();
    }

    private static void ladeDaten() throws DatenbankAccessException, DatenbankLeseException
    {
        oeffneDatenbank();
        ladeVokabeln();
        ladeKategorien();
        schliesseDatenbank();
        mapData();
    }

    private static void mapData()
    {
        for (Vokabel vokabel : vokabeln)
        {
            final ArrayList<Kategorie> katsForVok = new ArrayList<>();
            for (Kategorie kategorie : kategorien)
            {
                if (kategorie.liesVokabeln().contains(vokabel))
                {
                    katsForVok.add(kategorie);
                }
            }
            vokabel.setzeKategorien(katsForVok);
        }
    }

    /* ========================== */
    /* ==== Datenbank access ==== */
    /* ========================== */

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

    /* ======================= */
    /* ==== lade Methoden ==== */
    /* ======================= */

    private static void ladeVokabeln()
            throws DatenbankLeseException
    {
        ArrayList<Vokabel> ergebnis = new ArrayList<>();
        String sqlStmt = "SELECT wort, uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis, wiederholungen, anzahlrichtig ";
        sqlStmt += "FROM Vokabel";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            final ResultSet result = stmt.executeQuery();
            while (result.next())
            {
                ergebnis.add(new Vokabel(result.getString("wort"),
                        result.getString("uebersetzung"), result.getBytes("abbildung"),
                        result.getBytes("aussprache"), result.getString("lautschrift"),
                        result.getString("verwendungshinweis"),
                        result.getInt("wiederholungen"),
                        result.getInt("anzahlrichtig"))
                );
            }
            result.close();
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }
        vokabeln = ergebnis;
    }

    public static void ladeKategorien()
            throws DatenbankAccessException, DatenbankLeseException
    {
        final ArrayList<Kategorie> ergebnis = new ArrayList<>();
        String sqlStmt = "SELECT name ";
        sqlStmt += "FROM Kategorie ";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            final ResultSet result = stmt.executeQuery();
            while (result.next())
            {
                final String name = result.getString("name");
                final ArrayList<Vokabel> vokabeln = liesVokabelnForKat(name);
                ergebnis.add(new Kategorie(name, vokabeln));
            }
            result.close();
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.kategorie);
        }
        kategorien = ergebnis;
    }

    /* ======================= */
    /* ==== lies Methoden ==== */
    /* ======================= */

    public static Vokabel liesVokabel(String wort, String uebersetzung)
    {
        for (Vokabel vok : vokabeln)
        {
            if (vok.liesWort().equals(wort) && vok.liesUebersetzung().equals(uebersetzung))
            {
                return vok;
            }
        }
        return null;
    }

    public static Kategorie liesKategorie(String name)
    {
        for (Kategorie kat : kategorien)
        {
            if (kat.liesName().equals(name))
            {
                return kat;
            }
        }
        return null;
    }

    public static ArrayList<Vokabel> liesVokabelnForKat(String name)
            throws DatenbankAccessException, DatenbankLeseException
    {
        oeffneDatenbank();
        String sqlStmt = "SELECT Vokabel.wort, Vokabel.uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis, wiederholungen, anzahlrichtig ";
        sqlStmt += "FROM Vokabel, Beziehung ";
        sqlStmt += "WHERE name = ?";
        ArrayList<Vokabel> ergebnis = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            stmt.setString(1, name);
            final ResultSet result = stmt.executeQuery();
            while (result.next())
            {
                ergebnis.add(
                        new Vokabel(
                                result.getString("wort"),
                                result.getString("uebersetzung"),
                                result.getBytes("abbildung"),
                                result.getBytes("aussprache"),
                                result.getString("lautschrift"),
                                result.getString("verwendungshinweis"),
                                result.getInt("wiederholungen"),
                                result.getInt("anzahlrichtig")
                        )
                );
            }
            result.close();
        } catch (SQLException e)
        {
            throw new DatenbankLeseException(DatenbankObject.vokabel);
        }
        return ergebnis;
    }


    /* ============================= */
    /* ==== hinzufuege Methoden ==== */
    /* ============================= */

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
        String sqlStmt = "INSERT INTO vokabel (wort, uebersetzung, abbildung, aussprache, lautschrift, verwendungshinweis) ";
        sqlStmt += "VALUES (?, ?, ?, ?, ?, ?)";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
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
        vokabeln.add(dieVokabel);
        schliesseDatenbank();
        for (Kategorie kat : dieVokabel.liesKategorien())
        {
            vokabelZuKategorieHinzufuegen(dieVokabel, kat);
        }
    }

    public static void kategorieHinzufuegen(Kategorie dieKategorie) throws DatenbankAccessException, DuplicateKategorieException, DatenbankSchreibException
    {
        oeffneDatenbank();
        if (existiertKategorie(dieKategorie.liesName()))
        {
            throw new DuplicateKategorieException();
        }
        String sqlStmt = "INSERT INTO Kategorie (name) ";
        sqlStmt += "VALUES (?)";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            stmt.setString(1, dieKategorie.liesName());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new DatenbankSchreibException(DatenbankObject.kategorie);
        }
        kategorien.add(dieKategorie);
        schliesseDatenbank();
        for (Vokabel vok : dieKategorie.liesVokabeln())
        {
            vokabelZuKategorieHinzufuegen(vok, dieKategorie);
        }
    }

    private static void vokabelZuKategorieHinzufuegen(Vokabel vok, Kategorie kat) throws DatenbankAccessException, DatenbankSchreibException
    {
        if (kat.liesVokabeln().contains(vok))
        {
            if (vok.liesKategorien().contains(kat))
            {
                return;
            } else
            {
                kat.entferneVokabel(vok);
            }
        } else if (vok.liesKategorien().contains(kat))
        {
            vok.entferneKategorie(kat);
        }
        oeffneDatenbank();
        String sqlStmt = "INSERT INTO Beziehung (wort, uebersetzung, name) ";
        sqlStmt += "VALUES (?, ?, ?)";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            stmt.setString(1, vok.liesWort());
            stmt.setString(2, vok.liesUebersetzung());
            stmt.setString(3, kat.liesName());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new DatenbankSchreibException(DatenbankObject.vokabel);
        }
        vok.fuegeKategorieHinzu(kat);
        kat.fuegeVokabelHinzu(vok);
        schliesseDatenbank();
    }

    /* ========================== */
    /* ==== loesche Methoden ==== */
    /* ========================== */

    public static void loescheVokabel(String wort, String uebersetzung)
            throws DatenbankAccessException, DatenbankSchreibException
    {
        oeffneDatenbank();
        String sqlStmt = "DELETE FROM Vokabel ";
        sqlStmt += "WHERE wort = ? AND uebersetzung = ?";
        try
        {
            stmt = con.prepareStatement(sqlStmt);
            stmt.setString(1, wort);
            stmt.setString(2, uebersetzung);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new DatenbankSchreibException(DatenbankObject.vokabel);
        }
        vokabeln.remove(liesVokabel(wort, uebersetzung));
        schliesseDatenbank();
    }


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
        kategorien.remove(liesKategorie(name));
        schliesseDatenbank();
    }

    /* ======================== */
    /* ==== logik Methoden ==== */
    /* ======================== */

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

    private static boolean existiertVokabel(String wort, String uebersetzung)
    {
        return vokabeln.contains(
                new Vokabel(
                        wort,
                        uebersetzung,
                        null,
                        null,
                        null,
                        null,
                        0,
                        0
                )
        );
    }

    private static boolean existiertKategorie(String name)
    {
        return kategorien.contains(new Kategorie(name));
    }
}
