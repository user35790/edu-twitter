package com.test.dao;

import com.test.model.User;

import java.util.List;

public interface UserDAO {

    Integer getCountUsers();

    boolean isLoginNotExist(String login);

    List<User> getUsers();

    User get(int id);

    void create(User user, String password);

    void updateInfo(User user);

    void updatePassword(int id, String password);

    void updateStatus(int id, String status);

    void updateImage(int id, String image);

    void delete(int id);

}
