package com.wenky.example.spring.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-09 17:28
 */
public class BeanHandleProxy implements BeanFactoryPostProcessor, BeanNameAware {
    String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        System.out.println("BeanHandleProxy - postProcessBeanFactory");
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(Factory.class);
        definition.setScope(BeanDefinition.SCOPE_SINGLETON);
        definition.setAutowireCandidate(true);
        registry.registerBeanDefinition("proxy-01", definition);
    }

    public static class Factory
            implements FactoryBean<ProxyInterface>, BeanNameAware, InvocationHandler {
        String name;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Factory - invoke:" + method.getName());
            return null;
        }

        @Override
        public void setBeanName(String name) {
            this.name = name;
        }

        @Override
        public ProxyInterface getObject() throws Exception {
            System.out.println("Factory - getObject");
            ProxyInterface proxy =
                    (ProxyInterface)
                            Proxy.newProxyInstance(
                                    this.getClass().getClassLoader(),
                                    new Class[] {ProxyInterface.class},
                                    this);
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return ProxyInterface.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }
}
