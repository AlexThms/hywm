package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.SMSUtils;
import com.example.hywm.common.ValidateCodeUtils;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.User;
import com.example.hywm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/19 15:01
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        String code = ValidateCodeUtils.generateValidateCode4String(4);
       // SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", phone, code);
        log.info("验证码:{}",code);
        session.setAttribute(phone, code);
        return Result.success(WMContonst.SuccessEnum.Success_SEND);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map map, HttpSession session) {
        try {
            String phone = (String) map.get("phone");
            String code = (String) map.get("code");
            String sendCode = (String) session.getAttribute(phone);
            if (StringUtils.isNotBlank(sendCode) && code.equals(sendCode)) {
                User user = userService.selectUser(phone);
                if (user == null) {
                    user = new User();
                    user.setPhone(phone);
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    user.setId(uuid);
                    userService.insertUser(user);
                }
                session.setAttribute("User",user.getId());
                return Result.success(user);
            }
            return Result.error(WMContonst.ErrorEnum.Error_LOGIN_04.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_LOGIN_05.getMsg());
        }
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest req){
        req.getSession().removeAttribute("User");
        return Result.success("退出成功");
    }
}
