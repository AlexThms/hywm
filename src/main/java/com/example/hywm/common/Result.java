package com.example.hywm.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> {

    private String code;

    private T data;

    private String msg;

    private Map map = new HashMap();

    public static <T> Result<T> success(T obj){
        Result<T> response = new Result<>();
        response.code = "200";
        response.data = obj;
        return response;
    }

    public static <T> Result<T> error(String code, String msg){
        Result<T> response = new Result<>();
        response.code = code;
        response.msg = msg;
        return response;
    }

    public Result<T> add(String key, Object value){
        this.map.put(key,value);
        return this;
    }
}
