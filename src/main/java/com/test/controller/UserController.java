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

    @RequestMapping("/new_user")
    public void createUser(@RequestParam(name="login", required=true) String login,
                           @RequestParam(name="password", required=true) String password,
                           @RequestParam(name="name", required=false, defaultValue="anon") String name,
                           @RequestParam(name="sex", required=false, defaultValue="true") boolean sex) {
        userDAO.createUser(new User(login, name, sex), password);
    }

    // TODO: проверка поля
    @RequestMapping("/get_user")
    public User getUser(@RequestParam(name ="id", required=true, defaultValue="1") int id) {
        return userDAO.getUser(id);
    }

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    // TODO: проверка полей
    @RequestMapping("/update_info")
    public void updateInfo(@RequestParam(name ="name", required=false, defaultValue="1") String name,
                        @RequestParam(name ="login", required=false, defaultValue="1") String login,
                        @RequestParam(name ="date_birthday", required=false, defaultValue="1") Date dateBirthday,
                        @RequestParam(name ="sex", required=false, defaultValue="1") boolean sex) {
        userDAO.updateUserInfo(new User(name, login, dateBirthday, sex));
    }

    // TODO: проверка пароля
    @RequestMapping("/update_password")
    public void updatePassword(@RequestParam(name ="id", required=true, defaultValue="1") int id,
                               @RequestParam(name ="password", required=false, defaultValue="1") String password) {
        userDAO.updateUserPassword(id, password);
    }

    // TODO: проверка поля
    @RequestMapping("/update_status")
    public void updateStatus(@RequestParam(name ="id", required=true, defaultValue="1") int id,
                             @RequestParam(name ="status", required=false, defaultValue="1") String status) {
        userDAO.updateUserStatus(id, status);
    }

    // TODO: проверка поля
    @RequestMapping("/update_image")
    public void updateImage(@RequestParam(name ="id", required=true, defaultValue="1") int id,
                            @RequestParam(name ="image", required=false, defaultValue="1") String image) {
        userDAO.updateUserImage(id, image);
    }

    // TODO: проверка поля
    @RequestMapping("/delete_user")
    public void deleteUser(@RequestParam(name ="id", required=true) int id) {
        userDAO.deleteUser(id);
    }
}