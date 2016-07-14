package client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by pasha on 17.06.2016.
 */
public class Client {
    private Socket serverSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String host;

    public Client() {
        serverSocket = null;
        host = "127.0.0.1";
    }

    public void createSocket(){
        try {
            serverSocket = new Socket(JOptionPane.showInputDialog("Host"), 5225);
            System.out.println("Client is running");
            outputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            inputStream = new ObjectInputStream(serverSocket.getInputStream());
            outputStream.flush();
        } catch (IOException e) {
            createSocket();
        }

        /*try {
            host = JOptionPane.showInputDialog("Host");
            serverSocket = new Socket(host, 7890);
            System.out.println("Yea client");
            inputStream =new ObjectInputStream(serverSocket.getInputStream());
            outputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            outputStream.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            createSocket();
        }*/
    }

    public void closeConnection() throws IOException {
        outputStream.close();
        inputStream.close();
        serverSocket.close();
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public String getHost() {
        return host;
    }
}
