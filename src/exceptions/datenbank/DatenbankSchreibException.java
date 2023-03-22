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
        return String.format("Fehler beim Schreiben der %s", typ.name());
    }
}
