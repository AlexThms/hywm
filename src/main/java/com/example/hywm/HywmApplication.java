package com.example.hywm;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.hywm.mapper")
@ServletComponentScan
public class HywmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HywmApplication.class, args);
        log.info("启动类启动");
    }

}
