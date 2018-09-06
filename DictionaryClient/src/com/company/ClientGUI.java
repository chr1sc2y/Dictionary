//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientGUI{
    private JPanel panel1;
    public JTabbedPane tabbledPane;

    //Search
    private JPanel panelSearch;
    private JTextField textFieldSearch;
    private JButton buttonSearch;
    private JTextArea textAreaSearch;

    //Insert
    private JPanel panelInsert;
    private JTextField textFieldInsert;
    private JButton buttonInsert;
    private JTextArea textAreaInsert;

    //Delete
    private JPanel panelDelete;
    private JTextField textFieldDelete;
    private JButton buttonDelete;
    private JTextArea textAreaDelete;
    private JScrollPane scrollPaneSearch;
    private JScrollPane scrollPaneInsert;
    private JScrollPane scrollPaneDelete;
    private JButton buttonDeleteExit;
    private JButton buttonInsertExit;
    private JButton buttonSearchExit;

    public ClientGUI(Client client) {
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringSearch = textFieldSearch.getText();
                ArrayList<String> meanings = client.Search(stringSearch);
                textAreaSearch.setText("");
                if (meanings != null) {
                    for (int i = 0; i < meanings.size(); ++i) {
                        textAreaSearch.append(String.valueOf(i + 1) + ". " + meanings.get(i) + '\n');
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Cannot find word \"" + stringSearch + "\"!", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringInsert = textFieldInsert.getText();
                String[] temp = textAreaInsert.getText().split("\\n");
                ArrayList<String> meanings = new ArrayList<String>(Arrays.asList(temp));
                boolean success = client.Insert(stringInsert, meanings);
                if (success)
                    JOptionPane.showMessageDialog(null, "Insert success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Word \"" + stringInsert + "\" already exists!", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringDelete = textFieldDelete.getText();
                boolean success = client.Delete(stringDelete);
                if (success)
                    JOptionPane.showMessageDialog(null, "Delete success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Word \"" + stringDelete + "\"does not exist!", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonSearchExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.Close();
                System.exit(0);
            }
        });
        buttonInsertExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.Close();
                System.exit(0);
            }
        });
        buttonDeleteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.Close();
                System.exit(0);
            }
        });
    }
}
