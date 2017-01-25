package controller;

import javafx.event.ActionEvent;
import model.ServerModel;

import java.net.Socket;

public class ServerController {


    private ServerModel serverModel;

    public void initialize(){
        serverModel = new ServerModel();
    }

    public void handleStartServer(ActionEvent event) {
        Thread connection = new Thread(serverModel);
        connection.start();
    }

    public void handleStopServer(ActionEvent event) {

    }
}
