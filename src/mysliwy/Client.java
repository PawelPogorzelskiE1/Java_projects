package mysliwy;
import java.net.*;
import java.io.*;

public class Client {
    public Client()
    {
        try
        {
            Socket client = new Socket ("localhost", 5000);
            System.out.println("halo tu klient");
        }
        catch (java.io.IOException e)
        {
            System.out.println("Exception Client.java");
        }
    }
}
