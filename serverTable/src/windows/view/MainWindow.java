package windows.view;

import controllers.ConnectWithClient;
import server.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pasha on 22.06.2016.
 */
public class MainWindow {

    private JFrame frame;
    private JTextArea textArea;
    private Server server;

    private ServerSocket serverSocket;
    private Socket socket;
    //private Connection connection;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private ConnectWithClient connection;

    public MainWindow() {
        frame = new JFrame("Server");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea));
        //	frame.add(serverPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setVisible(true);

        startRunningServer();
    }

    public void startRunningServer() {
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(5225);
            textArea.append("Server is Running");

            while (true) {
                socket = serverSocket.accept();
                textArea.append("\nClient "
                        + socket.getInetAddress().getCanonicalHostName()
                        + " connected");

                connection = new ConnectWithClient(textArea, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
