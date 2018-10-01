package com.test.model;

public class Comment {

    private final int id;
    private final int idCreator;

    private String text;


    public Comment(int id, int idCreator, String text) {
        this.id = id;
        this.idCreator = idCreator;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
