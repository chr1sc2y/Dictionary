package com.company;

public class DictionaryServer {

    public static void main(String[] args) {
//        InputHandler inputHandler = new InputHandler(args);
//        boolean parameterHandler = inputHandler.ParameterHandler();
//        if (!parameterHandler)
//            return;
//        int port = Integer.parseInt(args[0]);
//        String dictionaryPath = args[1];
//
//        Dictionary dictionary = new Dictionary(dictionaryPath);
//        System.out.println(dictionary.getSize());
//        Server server = new Server(port, dictionary);
//        dictionary.Operation();

        ServerGUI serverGUI = new ServerGUI();
        Frame frame = new Frame(serverGUI);

        Dictionary dictionary = new Dictionary("/Users/zintrulcre/Course/Distributed System/Project/Project 1/Data/Dict.txt");
        System.out.println(dictionary.getSize());
        Server server = new Server(6666, dictionary, serverGUI);
        //dictionary.Operation();

        serverGUI.setServer(server);
        serverGUI.AddActionListener();
        frame.setServer(server);
        frame.AddActionListener();
    }
}
