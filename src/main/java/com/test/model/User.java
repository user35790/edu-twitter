package com.test.model;

public class User {

    private final int id;
    private final String name;
    private Tweet[] tweets;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
