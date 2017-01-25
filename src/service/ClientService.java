package service;

import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Krzysztof on 2017-01-25.
 */
public class ClientService {

    private List<PrintWriter> clientWriters;
    private List<String> onlineUsers;

    private InputStreamReader inputStream;
    private BufferedReader clientReader;
    @Setter
    private Socket clientSocket;
    private PrintWriter clientWriter;

    private String username;


    private static ClientService client;

    private ClientService(){
        clientWriters = new LinkedList<PrintWriter>();
        onlineUsers = new LinkedList<String>();
    }

    public static ClientService getInstance(){

        if (client == null){
            client = new ClientService();
        }

        return client;
    }


    public void addClientWriterToList() throws IOException {

        clientWriter = new PrintWriter(clientSocket.getOutputStream());
        clientWriters.add(clientWriter);

        System.out.println("Avaible client writers: " + clientWriters.size());

    }

    public void getUsernameFromMessage() throws IOException {

        inputStream = new InputStreamReader(clientSocket.getInputStream());
        clientReader = new BufferedReader(inputStream);

        if (clientReader.ready()){

            String messageFromClient = clientReader.readLine();
            if (messageFromClient.contains(">")) {
                int userIndex = messageFromClient.indexOf('>') - 1;
                username = messageFromClient.substring(0,userIndex);

                if (username.equals("Guest")){
                    username = randomizeGuestName();
                }
            }
            else{
                username = messageFromClient;
            }
        }

    }

    private String randomizeGuestName() {

        int random = (int) (Math.random() * 10000);
        String guestName = "Guest" + Integer.toString(random);
        return guestName;

    }

    public void addUsernameToOnlineUsers() throws IOException {

        if (username != null) {
            onlineUsers.add(username);
            System.out.println("Users online: " + onlineUsers.size());
            for (String x: onlineUsers){
                System.out.println(x);
            }
        } else {
            System.out.println("UserName is null");
        }

    }

}
