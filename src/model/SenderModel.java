package model;

import service.ClientService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Krzysztof on 2017-01-28.
 */
public class SenderModel {

    private List<Socket> clientSockets;
    private PrintWriter clientWriter;

    public SenderModel(){
        this.clientSockets = ClientService.getInstance().getClientSockets();
    }

    public void sendMessageToClients(String messageToClient) throws IOException {

        for (Socket client: clientSockets){

//            String message = getFullMessageWithTime(messageToClient);
            clientWriter = new PrintWriter(client.getOutputStream());
            clientWriter.println(messageToClient);
            clientWriter.flush();

        }

    }

    private String getMessageTime(){

        LocalDateTime messageTime = LocalDateTime.now();
        String time = messageTime.toString().substring(11,16);

        return time;

    }

    private String getFullMessageWithTime(String message){

        final String fullMessage = getMessageTime().concat("\n      " + message);
        return  fullMessage;

    }
}
