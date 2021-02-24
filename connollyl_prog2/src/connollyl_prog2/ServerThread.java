package connollyl_prog2;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * This class creates thread for the encryption server and runs input strings from
 * the client through the encryption process. ServerThread will take output strings
 * from the client, encrypt them, and send them back to the client using a printwriter.
 * All connections, disconnects, and encrypted strings are written to a log file.
 * @author Logan
 */
class ServerThread extends Thread
{
    private Socket clientSocket;
    private PrintWriter socketIO;
    private PrintWriter logWriter;
    private BufferedReader inFromClient;
    private PolyAlphabet cipher = new PolyAlphabet();
    
    /**
     * Creates a thread using the connected client socket and reference to a log
     * file writer.
     * @param clientSock
     * @param logfile 
     */
    public ServerThread (Socket clientSock, PrintWriter logfile) //constructor
    {
        this.clientSocket = clientSock;
        this.logWriter = logfile;
    }
    
    /**
     * This method takes in a number of strings from the client(s) and encrypts
     * the strings until the client sends a quit message.
     */
    @Override
    public void run()
    {
        try
        {
        inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        socketIO = new PrintWriter(clientSocket.getOutputStream(), true);
        Date date = new Date();
        logWriter.println("Got a connection: " + date.toString() + " /" + clientSocket.getInetAddress()
        + " Port: " + clientSocket.getPort() + "\n");
        String inSentence = inFromClient.readLine();
        while(!inSentence.equals("quit") && !inSentence.equals("Quit"))
        {
           String encrypted = cipher.encrypt(inSentence);
           logWriter.println("Encrypted text: " + encrypted + "\n");
           socketIO.println(encrypted);
           inSentence = inFromClient.readLine();
        }
        socketIO.println("Good Bye!\n");
        logWriter.println("Connection closed. Port: " + clientSocket.getPort()
                + "\n");
        clientSocket.close();
        }
        catch(Exception e)
        {
            logWriter.println(e + "Error\n");
        }
    }
}
