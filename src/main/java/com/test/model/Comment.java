package com.test.model;

import java.util.Date;

public class Comment {

    private final int id;
    private final int idCreator;
    private final int idTweet;

    private Date date;

    private String text;


    public Comment(int id, int idCreator, int idTweet, String text) {
        this.id = id;
        this.idCreator = idCreator;
        this.idTweet = idTweet;
        this.date = new Date();
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

    public int getIdTweet() {
        return idTweet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
