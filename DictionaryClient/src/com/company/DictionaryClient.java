//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

public class DictionaryClient {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler(args);
        boolean parameterHandler = inputHandler.ParameterHandler();
        if (!parameterHandler)
            return;
        String address = args[0];
        int port = Integer.parseInt(args[1]);

        Client client = new Client(address, port);
        int connect = client.clientConnect();
        if (connect != 0)
            return;
        ClientGUI clientGUI = new ClientGUI(client);
        Frame frame = new Frame(clientGUI, client);
    }
}
