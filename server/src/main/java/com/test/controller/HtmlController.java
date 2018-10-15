package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HtmlController {

    @GetMapping("/hello")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "hello";
    }


    @GetMapping
    public String index(Map<String, Object> model){
        model.put("some", "Hello, this is test twitter project");
        return "index";
    }
}
