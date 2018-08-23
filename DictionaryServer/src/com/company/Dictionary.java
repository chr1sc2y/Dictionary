package com.company;

import java.io.*;
import java.util.*;

public class Dictionary {

    private Map<String, List<String>> dict;
    FileInputStream fileInputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;

    public Dictionary(String dictionaryPath) {
        try {
            fileInputStream = new FileInputStream(dictionaryPath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            dict = new HashMap();
            String temp, word = "", meaning;
            List<String> meanings = new ArrayList<>();
            System.out.println("> Reading dictionary file!");
            while ((temp = bufferedReader.readLine()) != null) {
                int size = temp.length();
                char tailChar = temp.charAt(size - 1);
                if (tailChar == '"') {
                    meaning = temp.substring(1, size - 1);
                    meanings.add(meaning);
                } else if (tailChar == '[') {
                    word = temp.substring(1, size - 4);
                } else if (tailChar == ']') {
                    dict.put(word, meanings);
                    meanings = new ArrayList<>();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getSize() {
        return dict.size();
    }

    public ArrayList<String> Search(String word) {
        if (dict.containsKey(word))
            return (ArrayList<String>) dict.get(word);
        return null;
    }

    public Boolean Insert(String word, ArrayList meanings) {
        if (dict.containsKey(word))
            return false;
        dict.put(word, meanings);
        return true;
    }

    public Boolean Delete(String word) {
        if (dict.containsKey(word)) {
            dict.remove(word);
            return true;
        }
        return false;
    }

    public void PrintDictionary() {
        for (Map.Entry<String, List<String>> entry : dict.entrySet()) {
            System.out.println("Word is: " + entry.getKey() + ". Meanings are:");
            int i = 1;
            for (String meaning : entry.getValue()) {
                System.out.println(i++ + ": " + meaning);
            }
        }
    }

    public void Operation() {
        Scanner in = new Scanner(System.in), operation = new Scanner(System.in);
        Boolean operate = true;
        while (operate) {
            System.out.println("Input the operation number: 1 for xxx, 2 for deletion, 4 for printing dictionary, 0 for exit");
            int i = in.nextInt();
            switch (i) {
                case 0:
                    operate = false;
                    break;
                case 2:
                    System.out.print("Input the word you want to delete: ");
                    String word = operation.next();
                    if (this.Delete(word))
                        System.out.println("Deletion succeed!");
                    else
                        System.out.println("Cannot find " + word + " in dictionary. Deletion failed!");
                    break;
                case 4:
                    this.PrintDictionary();
                    break;
                default:
                    System.out.println("Wrong number!");
            }
        }

    }
}
