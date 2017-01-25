package model;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.*;

/**
 * Created by Krzysztof on 2017-01-25.
 */

public class ServerModel implements Runnable{


    private Socket clientSocket;
    @Setter
    private volatile boolean continueListeningForClients = true;



    @Override
    public void run() {
        try {
            listenForConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForConnection() throws IOException {

        ServerSocket serverSocket = new ServerSocket(5000);

        while (continueListeningForClients){

            System.out.println("Waiting for client's conneciton...");
            clientSocket = serverSocket.accept();
            System.out.println("Client has connected to the server.");


        }

    }




}
