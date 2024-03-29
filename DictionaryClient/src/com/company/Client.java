//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private String address;
    private int port;

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public int clientConnect() {
        System.out.println("> Connecting...");
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            System.out.println("> Connecting failed.");
            System.out.println("> Please start the server first and check the port number.");
            return 1;
        }

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            return 2;
        }
        System.out.println("> Connect to server!");
        return 0;
    }

    public ArrayList<String> Search(String stringSearch) {
        try {
            Message message = new Message("Search", stringSearch);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

            message = (Message) objectInputStream.readObject();
            Status status = message.getStatus();
            if (status == Status.SEARCH_SUCCESS) {
                System.out.println("> Search success!");
                return message.getMeaningString();
            } else if (status == Status.SEARCH_FAIL)
                System.out.println("> Cannot find word \"" + stringSearch + "\", search failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean Insert(String stringInsert, ArrayList<String> meanings) {
        try {
            Message message = new Message("Insert", stringInsert, meanings);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

            message = (Message) objectInputStream.readObject();
            Status status = message.getStatus();
            if (status == Status.INSERT_SUCCESS) {
                System.out.println("> Insert success!");
                return true;
            } else if (status == Status.INSERT_FAIL)
                System.out.println("> Word \"" + stringInsert + "\" already exists, insert failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(String stringDelete) {
        try {
            Message message = new Message("Delete", stringDelete);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

            message = (Message) objectInputStream.readObject();
            Status status = message.getStatus();
            if (status == Status.DELETE_SUCCESS) {
                System.out.println("> Delete success!");
                return true;
            } else if (status == Status.DELETE_FAIL)
                System.out.println("> Cannot find word \"" + stringDelete + "\", delete failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void Close() {
        try {
            Message message = new Message("Exit");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}