package com.wenky.example.spring.conditional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeanTestTest {

    @Test
    public void test() {
        // 获取java相关的环境变量
        String VM_PARAM = System.getProperty("VM_PARAM");
        String VM_PARAM1 = System.getProperty("VM_PARAM1");
        System.out.println(String.format("VM_PARAM[%s], VM_PARAM1[%s]", VM_PARAM, VM_PARAM1));
    }
}
