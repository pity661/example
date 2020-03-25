

# 管道pipe
## 一.概念
### 1.java管道为运行在同一个JVM中的两个线程提供了通信能力
### 2.通信双方应该是同一个进程中的不同线程

PipedInputStream实例对象必须和PipedOutputStream实例对象进行连接而产生一个通信管道。

PipedInputStream读取PipedOutputStream向管道中写入的数据，一个线程的PipedInputStream对象能从
另一个线程的PipedOutputStream对象中读取数据。

Java NIO管道是两个线程之间的单向数据连接。Pipe有一个`source`通道和`sink`通道，sink通道写，source通道读。

## 注意点
1.管道为空时，读操作会被阻塞；管道满时，写操作会被阻塞。
2.管道内部读写不能多线程同步进行，有同步🔒机制
3.管道对象在内存中