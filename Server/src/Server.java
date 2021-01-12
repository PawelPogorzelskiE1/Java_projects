import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverS = new ServerSocket(5000);
        Socket clientS = serverS.accept();

        System.out.println("blah blah");
    }
}
