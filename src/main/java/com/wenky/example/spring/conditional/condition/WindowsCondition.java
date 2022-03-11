package com.wenky.example.spring.conditional.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-08 11:13
 */
// 设置 VM options: -Dos.name=windows
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 当前环境信息
        Environment environment = context.getEnvironment();
        // bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取当前系统名
        String property = environment.getProperty("os.name").toUpperCase();
        return property.contains("WINDOWS");
    }
}
