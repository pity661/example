# channel通道
## 一、概念
channel用于在字节缓冲区和位于通道另一侧的实体(文件)之间有效的传输数据。

### 通道可以是单向或者双向的。
ReadableByteChannel 可读
WriteableByteChannel 可写

ByteChannel同时实现了上述两个接口。

### FileChannel的read方法执行步骤:
- 申请一块和缓存同大小的DirectByteBuffer。
- 读取数据到缓存，底层由NativeDispatcher的read实现。
- 把DirectByteBuffer的数据读取到用户定义的缓存，在jvm中分配内存。
### FileChannel的write方法执行步骤:
- 申请一块DirectByteBuffer，大小为ByteBuffer的limit - position。
- 复制byteBuffer中的数据到DirectByteBuffer中。
- 把数据从DirectByteBuffer写入到文件，底层由NativeDispatcher的write实现。
`*read和write方法都会导致数据复制两次。*`




