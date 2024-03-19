//package com.wenky.example.spring.config;
//
//import com.alibaba.fastjson.JSON;
//import com.wenky.demo.bean.auto.Wenky;
//import com.wenky.demo.bean.auto.property.WenkyProperties;
//import com.wenky.demo.bean.config.FactoryConfig;
//import com.wenky.demo.bean.config.ScanConfig;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * @program: example
// * @description:
// * @author: wenky
// * @email: huwenqi@panda-fintech.com
// * @create: 2020-04-30 11:04
// */
//@SpringBootTest
//public class ConfigTest {
//    @Autowired private FactoryConfig factoryConfig;
//    @Autowired private ScanConfig scanConfig;
//    @Autowired private Wenky wenky;
//    @Autowired private WenkyProperties properties;
//
//    @Test
//    public void test() {
//        System.out.println(
//                Optional.ofNullable(factoryConfig).map(FactoryConfig::getName).orElse(null));
//        System.out.println(Optional.ofNullable(scanConfig).map(ScanConfig::getName).orElse(null));
//    }
//
//    @Test
//    public void wenkyTest() {
//        System.out.println(wenky.getName());
//        System.out.println(JSON.toJSONString(properties));
//    }
//}
