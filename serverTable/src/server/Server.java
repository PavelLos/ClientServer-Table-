package server;

import controllers.ConnectWithClient;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pasha on 17.06.2016.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ConnectWithClient connect;

    public Server() {

    }

    public void createServerSocket(JTextArea textArea) {
        try {
            serverSocket = new ServerSocket(7890);
            while (true) {
                clientSocket = serverSocket.accept();
                //connect = new ConnectWithClient(textArea, this);
            }

        } catch (IOException e) {
            System.out.println("ERROR PORT 7890");
            e.printStackTrace();
        }
    }

    public void startServer(){
        try{
            /*clientSocket = serverSocket.accept();
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());*/
            outputStream.flush();

        } catch (IOException e) {
            System.out.println("ERROR CONNECTION");
            e.printStackTrace();
        }
    }

    public void createMessage(){

    }

    private void closeConnection() throws IOException {
        outputStream.close();
        inputStream.close();
        serverSocket.close();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}


