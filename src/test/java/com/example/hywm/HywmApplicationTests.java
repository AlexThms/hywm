package com.example.hywm;

import com.example.hywm.entity.Employee;
import com.example.hywm.service.EmployeeService;
import com.example.hywm.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class HywmApplicationTests {

    @Test
    void contextLoads() {
        String flavorUuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(flavorUuid);
    }

}
