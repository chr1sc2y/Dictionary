package com.company;

import java.io.File;
import java.util.regex.Pattern;

public class InputHandler {
    private String[] args;

    public InputHandler(String[] args) {
        this.args = args;
        System.out.println(args.length);
        for (int i = 0; i < args.length; ++i)
            System.out.println(args[i]);
    }

    public boolean ParameterHandler() {
        int size = args.length;
        if (size == 1) {
            if (args[0].equals("-h")) {
                System.out.println("> java -jar DictionaryClient.jar <address> <port>");
                System.out.println("> <port> should be integer from \"49152\" to \"65535\"");
                return false;
            }
        } else if (size == 2) {
            boolean addressHandler = AddressHandler(args[0]);
            if (!addressHandler)
                return false;
            boolean portHandler = PortHandler(args[1]);
            if (!portHandler)
                return false;
            return true;
        }
        System.out.println("> Wrong parameters, input -h to get help information.");
        return false;
    }

    private boolean AddressHandler(String addressString) {
        Pattern pattern = Pattern.compile("(\\d{1,3}.){3}\\d{1,3}");
        boolean match = pattern.matcher(addressString).matches();
        if (!match) {
            System.out.println("> Address is wrong.");
            System.out.println("> Please input an address between 0.0.0.0 and 255.255.255.255.");
            return false;
        }
        boolean addressParse = AddressParse(addressString);
        if (!addressParse) {
            System.out.println("> Address is wrong.");
            System.out.println("> Please ensure each integer is between 0 and 255.");
            return false;
        }
        System.out.println(addressString);
        return true;
    }

    private boolean AddressParse(String addressString) {
        String[] addressSplit = addressString.split("\\.");
        System.out.println("String Split test!");
        System.out.println(addressSplit.length);
        for (int i = 0; i < addressSplit.length; ++i) {
            System.out.println(addressSplit[i]);
            int addressNumber = Integer.parseInt(addressSplit[i]);
            if (addressNumber < 0 || addressNumber > 255)
                return false;
        }
        return true;
    }

    private boolean PortHandler(String portString) {
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
}
