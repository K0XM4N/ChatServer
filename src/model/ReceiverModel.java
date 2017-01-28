package model;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

/**
 * Created by Krzysztof on 2017-01-28.
 */
@Getter
@Setter
public class ReceiverModel implements Runnable {


    private String message;
    private Socket clientSocket;

    private InputStreamReader clientInputStream;
    private BufferedReader clientReader;

    public ReceiverModel(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientInputStream= new InputStreamReader(clientSocket.getInputStream());
        this.clientReader = new BufferedReader(clientInputStream);


    }

    @Override
    public void run() {
        try {
            receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String getMessageFromClient() throws IOException {

        return clientReader.readLine();
    }

    private void receiveMessage() throws IOException {

        while (true){
            String message = null;
            if ((message = getMessageFromClient()) != null){
                SenderModel messageSender = new SenderModel();
                System.out.println(message);
                messageSender.sendMessageToClients(message);
            }

        }

    }
}
