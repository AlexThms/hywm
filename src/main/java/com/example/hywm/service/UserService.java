package com.example.hywm.service;

import com.example.hywm.entity.User;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/19 15:01
 **/
public interface UserService {
    User selectUser(String phone) throws Exception;

    Boolean insertUser(User user) throws Exception;
}
