package com.wenky.example.spring.dynamic;

import org.springframework.beans.factory.InitializingBean;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-09 17:47
 */
public class OrderBean implements InitializingBean {

    public ProxyInterface proxyInterface;

    void initMethod() {
        System.out.println("OrderBean - initMethod");
    }

    public OrderBean() {
        System.out.println("OrderBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("OrderBean - afterPropertiesSet");
        if (proxyInterface == null) {
            System.out.println("proxyInterface is null");
        } else {
            System.out.println("proxyInterface is not null");
            proxyInterface.cryOut();
        }
    }
}
