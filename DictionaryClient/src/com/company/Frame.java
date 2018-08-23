package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Frame {
    public Frame(ClientGUI clientGUI, Client client) {
        JFrame jFrame = new JFrame();
        jFrame.add(clientGUI.tabbledPane);
        Toolkit tk = Toolkit.getDefaultToolkit();
        jFrame.setSize(800, 600);
        Dimension dimension = tk.getScreenSize();
        int xPosition = (dimension.width / 2) - (jFrame.getWidth() / 2);
        int yPosition = (dimension.height / 2) - (jFrame.getHeight() / 2);
        jFrame.setLocation(xPosition, yPosition);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                client.Close();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
