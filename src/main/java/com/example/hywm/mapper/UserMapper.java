package com.example.hywm.mapper;

import com.example.hywm.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/19 15:01
 **/
@Repository
public interface UserMapper {

    User selectUserByPhone(String phone);

    Integer insertUser(User user);

    String selectById(String id);
}
