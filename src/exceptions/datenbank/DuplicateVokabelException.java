package exceptions.datenbank;

public final class DuplicateVokabelException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Diese Vokabel existiert bereits.";
    }
}
