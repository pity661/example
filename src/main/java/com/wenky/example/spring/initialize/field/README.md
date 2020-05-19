资料:
spring容器加载1 - 容器构造 https://www.jianshu.com/p/86b53e3f0264
spring容器加载2 - 容器刷新 https://www.jianshu.com/p/5836d3d6dc72

前置处理:
# spring 先扫描包下注解bean进行注入
### 按顺序添加定义所有需要加载的bean
DefaultListableBeanFactory::registerBeanDefinition
### 1.根据启动类获取 basePackages
basePackages => ComponentScanAnnotationParser::parse 根据入口类，截取类名前的包名获取(如com.wenky.database.demo.SpringBootApplication::com.wenky.database.demo)
### 2.扫描所有配置类注入
ClassPathBeanDefinitionScanner::doScan(String... basePackages)
### 3.按顺序添加到DefaultListableBeanFactory::beanDefinitionMap属性
DefaultListableBeanFactory::registerBeanDefinition
### 按顺序依次加载bean
DefaultListableBeanFactory::preInstantiateSingletons => 调用getBean方法

singletonObjects 存放初始化好的bean
earlySingletonObjects(二级缓存) 刚实例化好但是未配置属性和初始化的bean
singletonFactories(三级缓存) 存放刚实例化的bean,通过ObjectFactory,   进入二级缓存前经过beanPostProcessor的getEarlyReference处理返回

Spring IOC 依赖注入，控制反转 构造方法循环依赖检测 
Spring Bean的生命周期只有四个阶段
 - 实例化
 - 属性赋值
 - 初始化
 - 销毁

实例化
1.AbstractBeanFactory::doGetBean 
2.1 -> AbstractAutowireCapableBeanFactory::createBean ::doCreateBean ::createBeanInstance(实例化) ::populateBean(依赖注入) ::initializeBean(初始化)
2.2 -> DefaultSingletonBeanRegistry::getSingleton ::beforeSingletonCreation(构造方法循环依赖检测)
3.1 populateBean -> AutowiredAnnotationBeanPostProcessor::postProcessProperties 1422 -> InjectionMetadata::inject -> 
InjectionMetadata::inject -> AutowiredFieldElement::inject -> AutowiredAnnotationBeanPostProcessor::inject -> 
DefaultListableBeanFactory::resolveDependency ::doResolveDependency -> DependencyDescriptor::resolveCandidate -> 
AbstractApplicationContext::getBean -> AbstractBeanFactory::getBean ::doGetBean(控制反转循环) 

容器刷新

AnnotationConfigApplicationContext::AnnotationConfigApplicationContext -> 