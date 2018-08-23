package com.company;

import java.util.ArrayList;
import java.io.Serializable;

enum Status {SEARCH_SUCCESS, SEARCH_FAIL, INSERT_SUCCESS, INSERT_FAIL, DELETE_SUCCESS, DELETE_FAIL, EXIT, ERROR}

public class Message implements Serializable {
    private String operationString;
    private String wordString;
    private ArrayList<String> meaningString;
    private Status status;

    public void setOperationString(String operationString) {
        this.operationString = operationString;
    }

    public void setWordString(String wordString) {
        this.wordString = wordString;
    }

    public void setMeaningString(ArrayList<String> meaningString) {
        this.meaningString = meaningString;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOperationString() {
        return operationString;
    }

    public String getWordString() {
        return wordString;

    }

    public ArrayList<String> getMeaningString() {
        return meaningString;
    }

    public Status getStatus() {
        return status;
    }

    public Message(String operationString) {
        this.operationString = operationString;
    }

    public Message(String operationString, String wordString) {
        this.operationString = operationString;
        this.wordString = wordString;
    }

    public Message(String operationString, String wordString, ArrayList<String> meaningString) {
        this.operationString = operationString;
        this.wordString = wordString;
        this.meaningString = meaningString;
    }
}
