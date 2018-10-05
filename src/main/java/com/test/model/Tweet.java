package com.test.model;

import java.util.Date;

public class Tweet {

    private int id;
    private final int idCreator;

    private Date dateLastWrite;

    private int likes;
    private int[] comments;

    private String text;

    public Tweet(int idCreator, String text) {
        this.idCreator = idCreator;
        this.dateLastWrite = new Date();
        this.text = text;
    }

    public void setDateLastWrite(Date dateLastWrite) {
        this.dateLastWrite = dateLastWrite;
    }

    public int getId() {
        return id;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public Date getDateLastWrite() {
        return dateLastWrite;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int[] getComments() {
        return comments;
    }

    public void setComments(int[] comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
