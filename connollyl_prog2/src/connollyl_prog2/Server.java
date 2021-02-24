package connollyl_prog2;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * This program is a server that will take in a string from one or many clients
 * and encrypt it using a polyalphabetic caeser cipher.
 * This class instantiates the server and runs it. The server listens on port 5764
 * and creates a thread for each client that connects to the server via localhost.
 * The log file is stored in the project folder. 
 * @author Logan
 */
public class Server 
{
    private ServerSocket serSock;
    private PrintWriter logWriter;
    /**
     * Instantiates the server and runs it
     * @param args 
     */
    public static void main(String args[])
    {
        Server server = new Server();
        server.run();
    }
    
    /**
     * Instantiates a log file writer and server socket for the server to listen
     * on, and creates new threads as clients join the server.
     */
    public void run()
    {
        try
        {
            File prog2 = new File("prog2.log");
            logWriter = new PrintWriter(new FileOutputStream(prog2), true);
            serSock = new ServerSocket(5764);
            while(true)
            {
                Socket clientSock = serSock.accept();
                ServerThread serThread = new ServerThread (clientSock, logWriter);
                serThread.start();
            }
        }
        catch (Exception e)
        {
            logWriter.println(e + "Error\n");
        }
    }
}