package com.test.controller;

import com.test.domain.Message;
import com.test.repos.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping("/hello")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "hello";
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/messages")
    public String messages(Map<String, Object> model){
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "messages";
    }

    @PostMapping("/messages")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model){
        Message message = new Message(text, tag);

        messagesRepo.save(message);

        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "messages";
    }

    @PostMapping("/messages/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()) {
            messages = messagesRepo.findByTag(filter);
        } else{
            messages = messagesRepo.findAll();
        }
            model.put("messages", messages);

        return "messages";
    }
}
