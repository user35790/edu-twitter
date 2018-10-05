package com.test.dao;

import com.test.model.User;

import java.util.List;

public interface UserDAO {

    Integer getCountUsers();

    boolean isLoginNotExist(String login);

    List<User> getUsers();

    User getUser(int id);

    void createUser(User user, String password);

    void updateUserInfo(User user);

    void updateUserPassword(int id, String password);

    void updateUserStatus(int id, String status);

    void updateUserImage(int id, String image);

    void deleteUser(int id);

}
