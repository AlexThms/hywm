package com.example.hywm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class WMContonst {
    public static final String PASSWORD ="123456";
    @Getter
    @AllArgsConstructor
    public enum ErrorEnum{
        Error_LOGIN_00("100","NOT LOGIN"),
        Error_LOGIN_01("101","用户不存在,登录失败"),
        Error_LOGIN_02("102","密码错误"),
        Error_LOGIN_03("103","账号已禁用，请联系管理员"),
        ;
        private String key;
        private String msg;

        public static String getErrorMsgByKey(String key){
            for(ErrorEnum errorEnum:ErrorEnum.values()){
                if(errorEnum.getKey().equals(key)){
                    return errorEnum.getMsg();
                }
            }
            return null;
        }
    }

}
