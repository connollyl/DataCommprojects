
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class FTPThread extends Thread
{
    private Socket clientSocket;
    private BufferedReader inFromClient;
    private String inSentence;
    private PrintWriter socketIO;
    private OutputStream os;
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    private final int FILE_SIZE = 1024;
    
        /**
     * Creates a thread using the connected client socket and reference to a log
     * file writer.
     * @param clientSock 
     */
    public FTPThread (Socket clientSock) //constructor
    {
        this.clientSocket = clientSock;
    }
    
    /**
     * This method will take a command from the client and send/receive a file
     * based on the command. It will also send a updated list of files.
     */
    @Override
    public void run()
    {
        try
        {
        inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        socketIO = new PrintWriter(clientSocket.getOutputStream(), true);
        os = clientSocket.getOutputStream();
        inSentence = inFromClient.readLine();
        while(!clientSocket.isClosed())
        {
            if(inSentence.equals("Refresh"))
            {
                listFiles();
            }
            if(inSentence.equals("Sending"))
            {
                System.out.println("Data Connection thru local port # " + clientSocket.getLocalPort()
                + " remote port # " + clientSocket.getPort());
                System.out.println("Receiving the file...");
                inSentence = inFromClient.readLine();
                getFile(inSentence);
            }
            if(inSentence.equals("Receiving"))
            {
                System.out.println("Data Connection thru local port # " + clientSocket.getLocalPort()
                + " remote port # " + clientSocket.getPort());
                System.out.println("Sending the file...");
                inSentence = inFromClient.readLine();
                sendFile(inSentence);
            }
            if(inSentence.equals("Disconnect"))
            {
                clientSocket.close();
            }
            else
            {
                inSentence = inFromClient.readLine();
            }
        }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Sends a string list of the files in the directory
     */
    private void listFiles()
    {
        try
        {
            File dir = new File("ServerFiles");
            File[] files = dir.listFiles();
            String fileList = "";
            for (int i = 0; i < files.length; i++) 
            {
                if(files[i].isFile())
                {
                    fileList = fileList + files[i].getName() + " ";
                }
            }
            socketIO.println(fileList);
        }
        catch (NullPointerException E)
        {
            System.out.println(E.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Sends the data of the given file as bytes to the client
     * @param fileName 
     */
    private void sendFile (String fileName)
    {
        try
        {
            File toSend = new File("ServerFiles\\" + fileName);
            System.out.println("File: " + fileName);
            System.out.println(toSend.length() + " Bytes sent");
            byte[] bytearray = new byte[(int) toSend.length()];
            FileInputStream fis = new FileInputStream(toSend);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray);
            os.write(bytearray);
            bis.close();
            fis.close();
            System.out.println("Connection Closed");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Retrieves a given files data that is sent from the client
     * @param fileName 
     */
    private void getFile (String fileName)
    {
        try
        {
        InputStream is = clientSocket.getInputStream();
        fos = new FileOutputStream("ServerFiles\\" + fileName);
        bos = new BufferedOutputStream(fos);
        int fileSize = 0;
        do
        {
            byte[] bytearray = new byte[FILE_SIZE];
            is.read(bytearray);
            bos.write(bytearray);
            fileSize += bytearray.length;
        } while (is.available() > 0);
        System.out.println("Got the File: " + fileName);
        System.out.println("Size: " + fileSize + " Bytes");
        bos.close();
        fos.close();
        System.out.println("Connection Closed");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
