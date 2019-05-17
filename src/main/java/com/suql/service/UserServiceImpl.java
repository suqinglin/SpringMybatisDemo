package com.suql.service;

import com.suql.bean.UserInfo;
import com.suql.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(String userName, int age, int sex) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setAge(age);
        userInfo.setSex(sex);
        userMapper.insert(userInfo);
    }
}
