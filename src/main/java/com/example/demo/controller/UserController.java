package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserList")
    public Map<String, Object> GetList() {
        Map<String, Object> map = new HashMap<>();
        map.put("wjf", "123");
        map.put("james", "321");
        return map;
    }
}
