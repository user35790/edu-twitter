package com.test.dao;

import com.test.model.User;

import java.util.List;

public interface UserDAO {

    Integer getCountUser();

    List<User> getUsers();

    void create(User user);

    void update(User user);

    void delete(int id);

}
