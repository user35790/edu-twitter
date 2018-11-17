package com.test.service;

import com.test.model.User;
import com.test.model.UserRole;
import com.test.model.dto.CaptchaResponceDto;
import com.test.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Value("${twitter.direction}")
    private String direction;

    @Value("${recaptha.secret}")
    private String recaptchaSecret;

    private final UserRepo userRepo;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    public UserService(UserRepo userRepo, MailSender mailSender, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
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
        userRepo.save(user);
        return "";
    }

    public void addSubscriber(Integer userSubscrId, User user) {
        if (!user.getId().equals(userSubscrId)) {
            User subscriber = userRepo.findFirstById(userSubscrId);
            User currentUSer = userRepo.findFirstById(user.getId());
            if (!subscriber.getFriends().contains(currentUSer)) {
                subscriber.getFriends().add(currentUSer);
                userRepo.save(subscriber);
            } else {
                LOGGER.error("User @{} already in subscribers user @{}", subscriber.getUsername(), user.getUsername());
            }
        } else {
            LOGGER.error("You cannot subscribe on myself");
        }
    }

    public void deleteSubscribe(Integer userSubscrId, User user) {
        if (!user.getId().equals(userSubscrId)) {
            User subscriber = userRepo.findFirstById(userSubscrId);
            User currentUSer = userRepo.findFirstById(user.getId());
            if (subscriber.getFriends().contains(currentUSer)) {
                subscriber.getFriends().remove(currentUSer);
                userRepo.save(subscriber);
            } else {
                LOGGER.error("User @{} not in subscribers @{}", subscriber.getUsername(), user.getUsername());
            }
        } else {
            LOGGER.error("You cannot subscribe on myself");
        }
    }

    public boolean addUser(User user, BindingResult bindingResult, Model model, String captchaResponse) {
        // valid check
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ServiceUtil.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("user", user);
            return false;
        }

        // check db
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("user", user);
            model.addAttribute("usernameError", "Username already exist");
            return false;
        }

        // captcha check
        if (!StringUtils.isEmpty(captchaResponse)) {
            String url = String.format(CAPTCHA_URL, recaptchaSecret, captchaResponse);
            CaptchaResponceDto response = restTemplate.postForObject(url, Collections.emptyList(),
                    CaptchaResponceDto.class);

            if (!response.isSuccess()) {
                model.addAttribute("captchaError", "Fill captcha");
                model.addAttribute("user", user);
                return false;
            }

            LOGGER.info("captcha success");
        } else {
            if (captchaResponse != null){
                model.addAttribute("captchaError", "Fill captcha");
                model.addAttribute("user", user);
                return false;
            }
        }

        // send mail
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Twitter Clone.\n" +
                            "Visit next link to activate account: %s/registration/activate/%s",
                    direction,
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
}
