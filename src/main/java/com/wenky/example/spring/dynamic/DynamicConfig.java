package com.wenky.example.spring.dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-09 17:36
 */
@Configuration
public class DynamicConfig {
    @Bean
    public BeanHandleProxy proxy() {
        return new BeanHandleProxy();
    }

    @Bean(initMethod = "initMethod")
    public OrderBean orderBean(ProxyInterface proxy) {
        OrderBean orderBean = new OrderBean();
        orderBean.proxyInterface = proxy;
        return orderBean;
    }
}
