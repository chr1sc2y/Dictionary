//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Frame {
    private JFrame jFrame;
    private Server server;

    public Frame(ServerGUI serverGUI) {
        jFrame = new JFrame();
        jFrame.setContentPane(serverGUI.panelServer);
        Toolkit tk = Toolkit.getDefaultToolkit();
        jFrame.setSize(800, 600);
        Dimension dimension = tk.getScreenSize();
        int xPosition = (dimension.width / 2) - (jFrame.getWidth() / 2);
        int yPosition = (dimension.height / 2) - (jFrame.getHeight() / 2);
        jFrame.setLocation(xPosition, yPosition);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void AddActionListener() {

        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                server.ServerClose(false);
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
