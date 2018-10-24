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

    private final static int NAME_LENGTH = 100;
    private final static int PASSWORD_LENGTH = 5;
    private final static int USERNAME_LENGTH = 5;

    private final UserRepo userRepo;
    private final MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    private boolean isCorrectName(String name) {
        if (name != null) {
            return (name.length() <= NAME_LENGTH);
        } else {
            return true;
        }
    }

    private boolean isCorrectPassword(String password) {
        return password.length() >= PASSWORD_LENGTH;
    }

    private boolean isCorrectUsername(String username) {
        return username.length() >= USERNAME_LENGTH;
    }

    public String addUser(User user) {
        if (!isCorrectUsername(user.getUsername())) {
            return "Username is not correct";
        }
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return "User with this username already exists!";
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
        if (!isCorrectName(user.getName())) {
            return "Name is not correct";
        }
        if (!isCorrectPassword(user.getPassword())) {
            return "Password is not correct";
        }
        userRepo.save(user);
        return "";
    }
}
