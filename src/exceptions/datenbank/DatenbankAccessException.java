package exceptions.datenbank;

public final class DatenbankAccessException extends Exception
{

    private final DatenbankAccessType type;

    public DatenbankAccessException(DatenbankAccessType type)
    {
        this.type = type;
    }

    @Override
    public String getMessage()
    {
        return String.format("Fehler beim %s der Datenbank", type.name());
    }
}
