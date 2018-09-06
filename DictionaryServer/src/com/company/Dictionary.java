//
//  Author: Zhengyu Chen
//  Student ID: 991678
//

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
            System.out.println("> Reading dictionary file succeed!");
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
            System.out.println("> Dictionary size is " + getSize() + '.');
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

    public synchronized ArrayList<String> Search(String word) {
        if (dict.containsKey(word))
            return (ArrayList<String>) dict.get(word);
        return null;
    }

    public synchronized boolean Insert(String word, ArrayList meanings) {
        if (dict.containsKey(word))
            return false;
        dict.put(word, meanings);
        return true;
    }

    public synchronized boolean Delete(String word) {
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
}
