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

    // TODO: генерация уникального логина/имени
    @RequestMapping("/new")
    public void createUser(@RequestParam(name="login") String login,
                           @RequestParam(name="password") String password,
                           @RequestParam(name="name", required=false, defaultValue="anon123") String name,
                           @RequestParam(name="sex", required=false) boolean sex) {
        userDAO.create(new User(login, name, sex), password);
    }

    @RequestMapping("/get")
    public User getUser(@RequestParam(name ="id") int id) {
        return userDAO.get(id);
    }

    @RequestMapping("/check")
    public boolean checkUser(@RequestParam(name="login") String login,
                             @RequestParam(name="password") String password){
        return userDAO.check(login, password);
    }

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @RequestMapping("/update_info")
    public void updateInfo(@RequestParam(name ="name", required=false) String name,
                           @RequestParam(name ="login", required=false) String login,
                           @RequestParam(name ="date_birthday", required=false) Date dateBirthday,
                           @RequestParam(name ="sex", required=false) boolean sex) {
        userDAO.updateInfo(new User(name, login, dateBirthday, sex));
    }

    // TODO: логика под проверку пароля
    @RequestMapping("/update_password")
    public void updatePassword(@RequestParam(name ="id") int id,
                               @RequestParam(name ="password") String password) {
        userDAO.updatePassword(id, password);
    }

    @RequestMapping("/update_status")
    public void updateStatus(@RequestParam(name ="id") int id,
                             @RequestParam(name ="status", required=false, defaultValue="") String status) {
        userDAO.updateStatus(id, status);
    }

    @RequestMapping("/update_image")
    public void updateImage(@RequestParam(name ="id") int id,
                            @RequestParam(name ="image", required=false, defaultValue="") String image) {
        userDAO.updateImage(id, image);
    }

    @RequestMapping("/delete")
    public void deleteUser(@RequestParam(name ="id") int id) {
        userDAO.delete(id);
    }
}