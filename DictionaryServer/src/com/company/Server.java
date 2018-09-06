//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Server {
    Dictionary dictionary;
    private int port;
    ServerGUI serverGUI;
    JTextArea textArea;

    ServerHandler serverHandler;
    Thread serverThread;
    boolean serverStarted = false;

    Map<Integer,Thread> threadList;

    public int getPort() {
        return port;
    }

    public void Print(String printString) {
        System.out.println(printString);
        textArea.append(printString + "\n");
    }


    public Server(int port, Dictionary dictionary, ServerGUI serverGUI) {
        this.dictionary = dictionary;
        this.port = port;
        this.serverGUI = serverGUI;
        textArea = serverGUI.getTextAreaInfo();
    }

    public void ServerStart() {
        if (serverStarted) {
            Print("> Server has already started!");
            return;
        }
        serverStarted = true;

        threadList = new HashMap<>();

        serverHandler = new ServerHandler(this.port);
        serverThread = new Thread(serverHandler);
        serverThread.start();
    }

    public void ServerClose(boolean buttonClose) {
        if (buttonClose) {
            if (!serverStarted) {
                Print("> Server has not started yet.");
                Print("> Please start the server first.");
                return;
            }
        }
        serverHandler.Close();
    }

    public class ServerHandler implements Runnable {
        private ServerSocket serversocket;
        int port;

        ServerHandler(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try {
                serversocket = new ServerSocket(this.port);
                Print("> Server port number is " + port + ".");
                Print("> Server thread starts!");
                Print("> Waiting for clients to connect...");
                while (true) {
                    Socket socket = serversocket.accept();
                    Print("> Client " + socket.getPort() + " has connected!");
                    ClientHandler clientHandler = new ClientHandler(socket);
                    Thread clientThread = new Thread(clientHandler);
                    threadList.put(socket.getPort(),clientThread);
                    clientThread.start();
                }
            } catch (SocketException e) {
                Print("> Server thread closed!");
                Print("> Server Exit.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void Close() {
            try {
                int size = threadList.size();
                if(size > 0) {
                    if(size > 1)
                        Print("> " + size + " clients are still running!");
                    else
                        Print("> " + "1 clinet is still running!");
                    Print("> Please close clients first!");
                    return;
                }
                serverStarted = false;
                serversocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ClientHandler implements Runnable {
        Socket socket;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;

        public ClientHandler(Socket socket) {
            try {
                Print("> Client thread starts!");
                this.socket = socket;
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean close = false;
            try {
                Message message;
                while ((message = (Message) objectInputStream.readObject()) != null) {
                    Print("> Client " + socket.getPort() + " send an operation: " + message.getOperationString());
                    switch (message.getOperationString()) {
                        case "Search":
                            ArrayList<String> meanings = dictionary.Search(message.getWordString());
                            if (meanings != null) {
                                message.setStatus(Status.SEARCH_SUCCESS);
                                message.setMeaningString(meanings);
                            } else
                                message.setStatus(Status.SEARCH_FAIL);
                            break;
                        case "Insert":
                            boolean operationInsert = dictionary.Insert(message.getWordString(), message.getMeaningString());
                            if (operationInsert)
                                message.setStatus(Status.INSERT_SUCCESS);
                            else
                                message.setStatus(Status.INSERT_FAIL);
                            break;
                        case "Delete":
                            boolean operationDelete = dictionary.Delete(message.getWordString());
                            if (operationDelete)
                                message.setStatus(Status.DELETE_SUCCESS);
                            else
                                message.setStatus(Status.DELETE_FAIL);
                            break;
                        case "Exit":
                            Print("> Client exiting...");
                            Close();
                            close = true;
                            break;
                        default:
                            message.setStatus(Status.ERROR);
                    }
                    if (close) {
                        Print("> Client " + socket.getPort() + " closed.");
                        break;
                    }
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void Close() {
            try {
                threadList.remove(socket.getPort());
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
