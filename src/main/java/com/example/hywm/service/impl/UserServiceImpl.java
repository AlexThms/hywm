package com.example.hywm.service.impl;

import com.example.hywm.entity.User;
import com.example.hywm.mapper.UserMapper;
import com.example.hywm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/19 15:01
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUser(String phone) throws Exception {
        User user = userMapper.selectUserByPhone(phone);
        return user;
    }

    @Override
    public Boolean insertUser(User user) throws Exception {
        Integer integer = userMapper.insertUser(user);
        if(integer != 1){
            return false;
        }
        return true;
    }
}
