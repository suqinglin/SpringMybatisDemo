package com.suql.controller;

import com.suql.request.RxUser;
import com.suql.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RxUser user) {
        try {
            String userName = user.getUserName();
            int age = user.getAge();
            int sex = user.getSex();
            userService.insert(userName, age, sex);
            return "succ";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
