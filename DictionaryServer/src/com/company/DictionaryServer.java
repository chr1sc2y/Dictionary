//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

public class DictionaryServer {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler(args);
        boolean parameterHandler = inputHandler.ParameterHandler();
        if (!parameterHandler)
            return;
        int port = Integer.parseInt(args[0]);
        String dictionaryPath = args[1];

        ServerGUI serverGUI = new ServerGUI();
        Frame frame = new Frame(serverGUI);

        Dictionary dictionary = new Dictionary(dictionaryPath);
        Server server = new Server(port, dictionary, serverGUI);

        serverGUI.setServer(server);
        serverGUI.AddActionListener();
        frame.setServer(server);
        frame.AddActionListener();
    }
}
