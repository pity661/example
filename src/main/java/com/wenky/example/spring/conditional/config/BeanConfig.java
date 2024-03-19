package com.wenky.example.spring.conditional.config;

//import com.wenky.demo.bean.config.ScanConfig;
import com.wenky.example.spring.conditional.bean.Person;
import com.wenky.example.spring.conditional.condition.LinuxCondition;
import com.wenky.example.spring.conditional.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-08 11:10
 */
// xxx 2
// @Conditional({LinuxCondition.class})
@Configuration
public class BeanConfig {
//    ScanConfig scanConfig;
    // xxx 1
    @Conditional({WindowsCondition.class})
    @Bean(name = "xxx")
    public Person person1() {
        return new Person("windows");
    }

    // xxx 1
    @Conditional({LinuxCondition.class})
    // xxx 3
    // @Conditional({LinuxCondition.class, FalseCondition.class})
    @Bean(name = "sss")
    public Person person2() {
        return new Person("linux");
    }
}
