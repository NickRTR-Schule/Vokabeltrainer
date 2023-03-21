package exceptions.datenbank;

public class DatenbankLeseException extends Exception
{
    private final DatenbankObject typ;

    public DatenbankLeseException(DatenbankObject object)
    {
        typ = object;
    }

    @Override
    public String getMessage()
    {
        return super.getLocalizedMessage();
    }
}
