//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

package com.company;

import java.io.File;
import java.util.regex.Pattern;

public class InputHandler {
    private String[] args;

    public InputHandler(String[] args) {
        this.args = args;
//        String[] arg = {};
//        String[] arg = {"-h"};
//        String[] arg = {"21","sadeg"};
//        String[] arg = {"98513","sadeg"};
//        String[] arg = {"51481","/Users/zintrulcre/Course/Distributed System/Project/Project 1/Data/testarg.txt"};
//        String[] arg = {"51481","/Users/zintrulcre/Course/Distributed System/Project/Project 1/Data/testarg.json"};
    }

    public boolean ParameterHandler() {
        int size = args.length;
        if (size == 1) {
            if (args[0].equals("-h")) {
                System.out.println("> java -jar DictionaryServer.jar <port> <dictionary-file>");
                System.out.println("> <port> should be integer from \"49152\" to \"65535\"");
                return false;
            }
        } else if (size == 2) {
            boolean portHandler = PortHandler(args[0]);
            if (!portHandler)
                return false;
            boolean fileHandler = FileHandler(args[1]);
            if (!fileHandler)
                return false;
            return true;
        }
        System.out.println("> Wrong parameters, input -h to get help information.");
        return false;
    }

    public boolean PortHandler(String portString) {
        Pattern pattern = Pattern.compile("[\\d]{5}");
        boolean match = pattern.matcher(portString).matches();
        if (!match) {
            System.out.println("> Port number is wrong.");
            System.out.println("> Please input an integer of 5 digits.");
            return false;
        }
        int port = Integer.parseInt(portString);
        if (port < 49152 || port > 65535) {
            System.out.println("> Port number is wrong.");
            System.out.println("> Please input an integer from 49152 to 65535");
            return false;
        }
        return true;
    }

    public boolean FileHandler(String fileString) {
        File file = new File(fileString);
        if (!file.exists()) {
            System.out.println("> File Path is wrong.");
            System.out.println("> The file does not exist.");
            System.out.println("> Please input a valid file path.");
            return false;
        }
        String fileExtension = fileString.substring(fileString.lastIndexOf('.') + 1, fileString.length());
        if (!fileExtension.equals("txt")) {
            System.out.println(fileExtension);
            System.out.println("> File name is wrong.");
            System.out.println("> The file is not a text file.");
            System.out.println("> Please input a valid file path.");
            return false;
        }
        return true;
    }
}
