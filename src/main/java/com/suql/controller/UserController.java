package com.suql.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.suql.request.RxUser;
import com.suql.security.UserDetailsImpl;
import com.suql.service.RedisService;
import com.suql.service.UserService;
import com.suql.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private RedisService redisService;

    @Resource
    private UserService userService;

    @Autowired // 就算没有getter和setter方法，也会被注入
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody RxUser user) {
        try {
            String userName = user.getUserName();
            int age = user.getAge();
            int sex = user.getSex();
            userService.insert(userName, age, sex);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody RxUser user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "failure";
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = JwtTokenUtil.createToken(1234L, redisService);
        return "token -> " + token + "\nuserDetails -> " + userDetails.toString();
    }
}
