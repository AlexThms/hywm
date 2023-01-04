package com.example.hywm.config;

import com.example.hywm.entity.Aliyun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootConfiguration
//public class CommonConfig {
//
//    @Value("${oss.appKey}")
//    private String appKey;
//    @Value("${oss.appSecret}")
//    private String appSecret;
//    @Value("${oss.bucket}")
//    private String bucket;
//    @Value("${oss.endPoint}")
//    private String endPoint;
//
//    @Bean
//    public Aliyun aliyun() {
//        return Aliyun.options().setAppKey(appKey).setAppSecret(appSecret).setBucket(bucket).setEndPoint(endPoint).build();
//    }
//}