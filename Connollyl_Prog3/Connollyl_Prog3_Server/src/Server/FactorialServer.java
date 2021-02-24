package Server;
import java.io.*;
import java.net.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Logan
 */
public class FactorialServer 
{
    private ServerSocket serSock;
    
    public static void main(String args[])
    {
        FactorialServer server = new FactorialServer();
        server.run();
    }

    private void run() 
    {
       try
        {
            serSock = new ServerSocket(5764);
            while(true)
            {
                Socket clientSock = serSock.accept();
                ServerThread serThread = new ServerThread (clientSock);
                serThread.start();
            }
        }
        catch (Exception e)
        {

        }
    }
}
