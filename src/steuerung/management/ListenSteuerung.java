package steuerung.management;

import java.util.ArrayList;

public interface ListenSteuerung<T>
{

    ArrayList<T> liesDaten() throws Exception;

    void loescheDatensatz(T obj) throws Exception;
}
