package com.example.lab_02.beans;

public class UserForm {

    private String word;
    private int size;

    public UserForm(String word, int size) {
        this.word = word;
        this.size = size;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
