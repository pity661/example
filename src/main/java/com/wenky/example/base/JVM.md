1. JVM栈
启动新线程，java虚拟机会分配一个栈。虚拟机只会对栈执行压栈或出栈。
每个线程包含一个栈区，只保存基本数据类型的对象和对象的引用，对象都存放在堆区。
每个栈中数据都是私有的，其他栈不能访问。
栈分为3个部分： 基本类型变量区、执行环境上下文、操作指令区。

2. 堆内存
存储对象，只有一个堆区所有线程共享，需要在运行时动态分配内存，存取速度较慢。

3. 方法区
存储每个类的信息(类名、方法信息、字段信息)、静态变量、常量及编译后的代码。
Class文件中除了类的字段、方法、接口等信息，还有一项信息是常量池，存储编译期间产生的字面量和符号引用。
运行时常量池也在方法区，类和接口被加载到JVM后(类加载原理)，对应的运行时常量池就被创建出来，String的intern方法可以在运行期间将新的常量放入运行时常量池。

4. 本地方法栈
native方法

5. 程序计数器
用于保存当前线程执行的内存地址。
线程私有，保证线程切换之后的状态恢复。