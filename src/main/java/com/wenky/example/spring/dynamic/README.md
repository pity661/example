Spring生命周期执行顺序 beanFactoryPostProcessor -> 普通Bean构造方法 -> 
设置依赖或属性 -> @PostConstruct注解的方法 -> InitializingBean接口指定方法 -> initMethod