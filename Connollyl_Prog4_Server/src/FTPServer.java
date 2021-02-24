
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Logan
 */
public class FTPServer {
    private ServerSocket serSock;
    private PrintWriter socketIO;

    public static void main(String args[])
    {
        FTPServer server = new FTPServer();
        server.run();
    }

    private void run() 
    {
       try
        {
            serSock = new ServerSocket(5721);
            System.out.println("FTP Server is Running");
            while(true)
            {
                Socket clientSock = serSock.accept();
                System.out.println("Got a Connection: " + clientSock.getLocalAddress()
                + " on Port # " + clientSock.getLocalPort() + " Remote Port # " 
                        + clientSock.getPort());
                FTPThread serThread = new FTPThread(clientSock);
                serThread.start();
            }
        }
        catch (Exception e)
        {

        }
    }
}
