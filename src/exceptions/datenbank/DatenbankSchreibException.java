package exceptions.datenbank;

public class DatenbankSchreibException extends Exception
{
    private final DatenbankObject typ;

    public DatenbankSchreibException(DatenbankObject object)
    {
        typ = object;
    }

    @Override
    public String getMessage()
    {
        return "Fehler beim Speichern der " + typ.name();
    }
}
