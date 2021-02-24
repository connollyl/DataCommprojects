/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Logan
 */
class ServerThread extends Thread
{
    private Socket clientSocket;
    private PrintWriter socketIO;
    private BufferedReader inFromClient;
    private FactorialCalculator FC = new FactorialCalculator();
    private long temp;
    private String inSentence;
    
    /**
     * Creates a thread using the connected client socket and reference to a log
     * file writer.
     * @param clientSock
     * @param logfile 
     */
    public ServerThread (Socket clientSock) //constructor
    {
        this.clientSocket = clientSock;
    }
    
    /**
     * This method takes in a number of strings from the client(s) and will 
     * convert the string to a long to calculate the factorial
     */
    @Override
    public void run()
    {
        try
        {
        inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        socketIO = new PrintWriter(clientSocket.getOutputStream(), true);
        inSentence = inFromClient.readLine();
        while(!inSentence.equals("quit") && !inSentence.equals("Quit"))
        {
            try
            {
                temp = Long.parseLong(inSentence);
                if(temp >= 2 || temp <= 25)
                {
                String factorial = FC.compute(temp);
                socketIO.println(factorial);
                inSentence = inFromClient.readLine();
                }
            }
            catch (Exception e)
            {
                socketIO.println("Enter a valid number.");
                inSentence = inFromClient.readLine();
            }
        }
        socketIO.println("Good Bye!\n");
        clientSocket.close();
        }
        catch(Exception e)
        {
            
        }
    }
}
