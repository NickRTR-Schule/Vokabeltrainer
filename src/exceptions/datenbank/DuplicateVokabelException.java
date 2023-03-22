package exceptions.datenbank;

public class DuplicateVokabelException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Diese Vokabel existiert bereits.";
    }
}
