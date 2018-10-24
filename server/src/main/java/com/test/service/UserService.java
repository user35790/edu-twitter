package com.test.service;

import com.test.model.User;
import com.test.model.UserRole;
import com.test.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;
    private MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    private boolean isCorrectName(String name) {
        return (name != null) && !(name.isEmpty());
    }

    private boolean isCorrectPassword(String password) {
        return (password != null) && !(password.isEmpty()) && (password.length() > 3);
    }

    private boolean isCorrectUsername(String username) {
        return (username != null) && !(username.isEmpty()) && (username.length() > 3);
    }

    private String checkUser(User user) {
        if (!isCorrectName(user.getName())) {
            return "Name is not correct";
        }

//        User userFromDb = userRepo.findByUsername(user.getUsername());
////        if (userFromDb != null){
////            return "User already exists!";
////        }

        if (!isCorrectPassword(user.getPassword())) {
            return "Password is not correct";
        }
        return "";
    }

    public String addUser(User user) {
        if (!isCorrectUsername(user.getUsername())) {
            return "Username is not correct";
        }
        if (!isCorrectPassword(user.getPassword())) {
            return "Password is not correct";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Twitter Clone.\n" +
                            "Visit next link to activate account: http://localhost:8080/registration/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return "";
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }


    public String editUserProfile(User user) {
        String message = checkUser(user);
        if (!message.equals("")) {
            return message;
        }

        userRepo.save(user);
        return "";
    }
}
