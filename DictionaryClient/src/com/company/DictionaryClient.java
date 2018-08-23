package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DictionaryClient {

    public static void main(String[] args) {
//        InputHandler inputHandler = new InputHandler(args);
//        boolean parameterHandler = inputHandler.ParameterHandler();
//        if (!parameterHandler)
//            return;
//        String address = args[0];
//        int port = Integer.parseInt(args[1]);
//
//        Client client = new Client(address, port);
//        ClientGUI clientGUI = new ClientGUI(client);

        Client client = new Client("127.0.0.1", 6666);
        ClientGUI clientGUI = new ClientGUI(client);

        Frame frame = new Frame(clientGUI, client);
    }
}
