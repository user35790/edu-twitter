package com.test.model;

import java.util.Date;

public class User {

    private int id;
    private String login;
    private String password;

    private String name;
    private int age;
    private boolean sex;
    private String image;

    private String status;
    private Date dateStart;

    private int[] tweets;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.dateStart = new Date();

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public int[] getTweets() {
        return tweets;
    }

    public void setTweets(int[] tweets) {
        this.tweets = tweets;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
