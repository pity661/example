package com.wenky.example.spring.conditional;

import com.wenky.example.spring.conditional.bean.Person;
import com.wenky.example.spring.conditional.config.BeanConfig;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-08 11:12
 */
public class BeanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);
    }
}
