package com.test.controller;

import com.test.dao.UserDAO;
import com.test.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // TODO: проверка поля
    // TODO: общий класс под сессию пользователя для запоминания id

    @RequestMapping("/new")
    public void createUser(@RequestParam(name="login", required=true) String login,
                           @RequestParam(name="password", required=true) String password,
                           @RequestParam(name="name", required=false, defaultValue="anon") String name,
                           @RequestParam(name="sex", required=false, defaultValue="true") boolean sex) {
        userDAO.create(new User(login, name, sex), password);
    }

    @RequestMapping("/get")
    public User getUser(@RequestParam(name ="id", required=true, defaultValue="1") int id) {
        return userDAO.get(id);
    }

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @RequestMapping("/update_info")
    public void updateInfo(@RequestParam(name ="name", required=false, defaultValue="1") String name,
                           @RequestParam(name ="login", required=false, defaultValue="1") String login,
                           @RequestParam(name ="date_birthday", required=false, defaultValue="1") Date dateBirthday,
                           @RequestParam(name ="sex", required=false, defaultValue="1") boolean sex) {
        userDAO.updateInfo(new User(name, login, dateBirthday, sex));
    }

    // TODO: логика под проверку пароля
    @RequestMapping("/update_password")
    public void updatePassword(@RequestParam(name ="id", required=true, defaultValue="1") int id,
                               @RequestParam(name ="password", required=false, defaultValue="1") String password) {
        userDAO.updatePassword(id, password);
    }

    @RequestMapping("/update_status")
    public void updateStatus(@RequestParam(name ="id", required=true, defaultValue="1") int id,
                             @RequestParam(name ="status", required=false, defaultValue="1") String status) {
        userDAO.updateStatus(id, status);
    }

    @RequestMapping("/update_image")
    public void updateImage(@RequestParam(name ="id", required=true, defaultValue="1") int id,
                            @RequestParam(name ="image", required=false, defaultValue="1") String image) {
        userDAO.updateImage(id, image);
    }

    @RequestMapping("/delete")
    public void deleteUser(@RequestParam(name ="id", required=true) int id) {
        userDAO.delete(id);
    }
}