package com.test.model;

import java.util.Date;

public class User {

    private int id;
    private String login;

    private String name;
    private Date dateBirthday;
    private boolean sex;
    private String image;

    private String status;

    private int[] tweets;

    // для создания нового пользователя
    public User(String login, String name, boolean sex) {
        this.login = login;
        this.name = name;
        this.sex = sex;
    }

    // для обновления информации о пользователе
    public User(String login, String name, Date dateBirthday, boolean sex) {
        this.login = login;
        this.name = name;
        this.dateBirthday = dateBirthday;
        this.sex = sex;
    }

    // для получения полной анкеты пользователя
    public User(String login, String name, Date dateBirthday, boolean sex, String image, String status) {
        this.login = login;
        this.name = name;
        this.dateBirthday = dateBirthday;
        this.sex = sex;
        this.image = image;
        this.status = status;
    }

    // для получения информации о пользователях
    public User(int id, String login, String name, String image) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.image = image;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date age) {
        this.dateBirthday = age;
    }

    public boolean getSex() {
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
