package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI {
    private Server server;
    public JPanel panelServer;
    private JButton buttonStart;
    private JTextArea textAreaInfo;
    private JButton buttonExit;

    public JTextArea getTextAreaInfo() {
        return textAreaInfo;
    }

    public ServerGUI() {
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void AddActionListener() {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.ServerStart();
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.ServerClose(true);
            }
        });
    }
}
