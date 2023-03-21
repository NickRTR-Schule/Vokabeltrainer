package exceptions.datenbank;


public class DatenbankAccessException extends Exception
{

    private final DatenbankAccessType type;

    public DatenbankAccessException(DatenbankAccessType type)
    {
        this.type = type;
    }

    @Override
    public String getMessage()
    {
        return "Fehler beim " + type.name() + "der Datenbank";
    }
}
