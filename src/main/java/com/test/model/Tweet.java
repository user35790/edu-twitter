package com.test.model;

import java.util.Date;

public class Tweet {

    private final int id;
    private final int idCreator;

    private Date dateLastWrite;

    private int likes;
    private Comment[] comments;

    private String text;

    public Tweet(int id, int idCreator, String text) {
        this.id = id;
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

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
