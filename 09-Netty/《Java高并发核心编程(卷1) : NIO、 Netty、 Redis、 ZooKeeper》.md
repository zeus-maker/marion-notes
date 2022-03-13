# 《Java高并发核心编程(卷1) : NIO、 Netty、 Redis、 ZooKeeper》

## 第1章 高并发时代的必备技能

随着5G应用、多终端应用、物联网应用、工业互联应用、大数据应用、人工智能应用的飞速发展，高并发开发时代已然到来，能够驾驭高并发和大数据的物联网架构师、高并发架构师、大数据架构师、Java高级工程师在人才市场也随之成为“香饽饽”，Netty、Redis、ZooKeeper、高性能HTTP服务器组件（如Nginx）、高并发Java组件（JUC包）等则成为广大Java工程师所必须掌握的开发技能。


### 1.1　Netty为何这么火

- 1.1.1　Netty火热的程度

	- 火爆的Kafka和RocketMQ等消息中间件、火热的ElasticSearch开源搜索引擎、大数据处理Hadoop的RPC框架Avro、分布式通信框架Dubbo，都使用了Netty。
	- 是因为它提供了异步的、事件驱动的网络应用程序框架和工具
	- API使用简单，开发门槛低。功能强大，预置了多种编解码功能，支持多种主流协议。定制能力强，可以通过ChannelHandler对通信框架进行灵活扩展。性能高，与其他业界主流的NIO框架相比，Netty的综合性能最优。成熟、稳定，Netty修复了在JDK NIO中所有已发现的Bug，业务开发人员不需要再为NIO的Bug而烦恼。社区活跃，版本迭代周期短，发现的Bug可以被及时修复。

## 第2章 高并发IO的底层原理

### 2.1 I0读写的基本原理

- 2.1.1　内核缓冲区与进程缓冲区
- 2.1.2　典型的系统调用流程

### 2.2四种主要的I0模型

- 2.2.1 同步阻塞IO
- 2.2.2 同步非阻塞IO
- 2.2.3 IO多路复用
- 2.2.4 异步IO

### 2.3通过合理配置来支持百万级并发连接

- 这里所涉及的配置就是Linux操作系统中文件句柄数的限制。在生产环境Linux系统中，基本上都需要解除文件句柄数的限制。原因是Linux系统的默认值为1024，也就是说，一个进程最多可以接受1024个socket连接，这是远远不够的。
- 文件句柄也叫文件描述符。在Linux系统中，文件可分为普通文件、目录文件、链接文件和设备文件。文件描述符（File Descriptor）是内核为了高效管理已被打开的文件所创建的索引，是一个非负整数（通常是小整数），用于指代被打开的文件。所有的IO系统调用（包括socket的读写调用）都是通过文件描述符完成的。

## 第3章　Java NIO核心详解

### 3.1　Java NIO简介

Java NIO类库包含以下三个核心组件：
1. Channel（通道）
2. Buffer（缓冲区）
3. Selector（选择器）


- 3.1.1　NIO和OIO的对比

	- （1）OIO是面向流（Stream Oriented）的，NIO是面向缓冲区（Buffer Oriented）的。

		- NIO不像OIO那样是顺序操作，它可以随意读取Buffer中任意位置的数据

	- （2）OIO的操作是阻塞的，而NIO的操作是非阻塞的。

		- NIO使用了通道和通道的多路复用技术

	- （3）OIO没有选择器（Selector）的概念，而NIO有选择器的概念。

		- NIO需要底层操作系统提供支持

- 3.1.2　通道

	- 在NIO中，一个网络连接使用一个通道表示，所有NIO的IO操作都是通过连接通道完成的。一个通道类似于OIO中两个流的结合体，既可以从通道读取数据，也可以向通道写入数据。

- 3.1.3　选择器

	- 1. 什么是IO多路复用？

		- IO多路复用指的是一个进程/线程可以同时监视多个文件描述符（含socket连接），一旦其中的一个或者多个文件描述符可读或者可写，该监听进程/线程就能够进行IO就绪事件的查询。

	- IO多路复用编程的第一步是把通道注册到选择器中，第二步是通过选择器所提供的事件查询（select）方法来查询这些注册的通道是否有已经就绪的IO事件（例如可读、可写、网络连接完成等）。

- 3.1.4　缓冲区

	- 应用程序与通道的交互主要是进行数据的读取和写入

### 3.2　详解NIO Buffer类及其属性

NIO的Buffer本质上是一个内存块，既可以写入数据，也可以从中读取数据。Java NIO中代表缓冲区的Buffer类是一个抽象类，位于java.nio包中。


- 3.2.1　Buffer类

	- 有8种缓冲区类，分别是ByteBuffer、CharBuffer、DoubleBuffer、FloatBuffer、IntBuffer、LongBuffer、ShortBuffer、MappedByteBuffer。

- 3.2.2　Buffer类的重要属性

	- 1. capacity（容量）

		- 1. Buffer类的capacity属性表示内部容量的大小。一旦写入的对象数量超过了capacity，缓冲区就满了，不能再写入了。
		- 2. Buffer类的capacity属性一旦初始化，就不能再改变
		- 3. capacity并不是指内部的内存块byte[]数组的字节数量，而是指能写入的数据对象的最大限制数量

	- 2. position（读写位置）

		- Buffer类的position属性表示当前的位置。position属性的值与缓冲区的读写模式有关
		- 写模式下

			- （1）在刚进入写模式时，position值为0，表示当前的写入位置为从头开始。
			- （2）每当一个数据写到缓冲区之后，position会向后移动到下一个可写的位置。
			- （3）初始的position值为0，最大可写值为limit-1。当position值达到limit时，缓冲区就已经无空间可写了。

		- 读模式下

			- （1）当缓冲区刚开始进入读模式时，position会被重置为0。
			- （2）当从缓冲区读取时，也是从position位置开始读。读取数据后，position向前移动到下一个可读的位置。
			- （3）在读模式下，limit表示可读数据的上限。position的最大值为最大可读上限limit，当position达到limit时表明缓冲区已经无数据可读。

		- Buffer的读写模式具体如何切换呢？

			- 可以调用fip()方法将缓冲区变成读模式
			- （1）limit属性被设置成写模式时的position值，表示可以读取的最大数据位置。（2）position由原来的写入位置变成新的可读位置，也就是0，表示可以从头开始读。

	- 3. limit（读写的限制）

		- Buffer类的limit属性表示可以写入或者读取的数据最大上限，其属性值的具体含义也与缓冲区的读写模式有关。

			- （1）在写模式下，limit属性值的含义为可以写入的数据最大上限。在刚进入写模式时，limit的值会被设置成缓冲区的capacity值，表示可以一直将缓冲区的容量写满。
			- （2）在读模式下，limit值的含义为最多能从缓冲区读取多少数据。

	- 4. mark（标记）属性

		- 在缓冲区操作过程当中，可以将当前的position值临时存入mark属性中；需要的时候，再从mark中取出暂存的标记值，恢复到position属性中，重新从position位置开始处理。

- 3.3　详解NIO Buffer类的重要方法

  本节将详细介绍Buffer类的几个常用方法，包含Buffer实例的创建、写入、读取、重复读、标记和重置等。
  

	- 3.3.1　allocate()
	- 3.3.2　put()

		- 在调用allocate()方法分配内存、返回了实例对象后，缓冲区实例对象处于写模式，可以写入对象，如果要把对象写入缓冲区，就需要调用put()方法。

	- 3.3.3　flip()

		- 向缓冲区写入数据之后，是否可以直接从缓冲区读取数据呢？不能！这时缓冲区还处于写模式，如果需要读取数据，要将缓冲区转换成读模式。fip()翻转方法是Buffer类提供的一个模式转变的重要方法，作用是将写模式翻转成读模式。
		- 在读取完成后，如何再一次将缓冲区切换成写模式呢？

			- 可以调用Buffer.clear()清空或者Buffer.compact()压缩方法，它们可以将缓冲区转换为写模式

	- 3.3.4　get()

		- 缓冲区是不是可以重复读呢？

			- 既可以通过倒带方法rewind()去完成，也可以通过mark()和reset()两个方法组合实现

	- 3.3.5　rewind()

		- 已经读完的数据，如果需要再读一遍，可以调用rewind()方法。rewind()也叫倒带，就像播放磁带一样倒回去，再重新播放。
		- rewind ()方法主要是调整了缓冲区的position属性与mark属性

			- （1）position重置为0，所以可以重读缓冲区中的所有数据。
			- （2）limit保持不变，数据量还是一样的，仍然表示能从缓冲区中读取的元素数量。
			- （3）mark被清理，表示之前的临时位置不能再用了。

	- 3.3.6　mark()和reset()

		- mark()和reset()两个方法是配套使用的：Buffer.mark()方法将当前position的值保存起来放在mark属性中，让mark属性记住这个临时位置；然后可以调用Buffer.reset()方法将mark的值恢复到position中。

	- 3.3.7　clear()

		- 在读模式下，调用clear()方法将缓冲区切换为写模式

	- 3.3.8　使用Buffer类的基本步骤

		- （1）使用创建子类实例对象的allocate()方法创建一个Buffer类的实例对象。
		- （2）调用put()方法将数据写入缓冲区中。
		- （3）写入完成后，在开始读取数据前调用Buffer.fip()方法，将缓冲区转换为读模式。
		- （4）调用get()方法，可以从缓冲区中读取数据。
		- （5）读取完成后，调用Buffer.clear()方法或Buffer.compact()方法，将缓冲区转换为写模式，可以继续写入。

- 3.4　详解NIO Channel类

  Java NIO中一个socket连接使用一个Channel来表示
  （1）FileChannel：文件通道，用于文件的数据读写。
  （2）SocketChannel：套接字通道，用于套接字TCP连接的数据读写。
  （3）ServerSocketChannel：服务器套接字通道（或服务器监听通道），允许我们监听TCP连接请求，为每个监听到的请求创建一个SocketChannel通道。
  （4）DatagramChannel：数据报通道，用于UDP的数据读写。
  

	- 3.4.1　FileChannel

		- FileChannel（文件通道）是专门操作文件的通道。通过FileChannel，既可以从一个文件中读取数据，也可以将数据写入文件中。特别申明一下，FileChannel为阻塞模式，不能设置为非阻塞模式。
		- 1. 获取FileChannel
		- 2. 读取FileChannel
		- 3. 写入FileChannel
		- 4. 关闭通道
		- 5. 强制刷新到磁盘

	- 3.4.2　使用FileChannel完成文件复制的实战案例
	- 3.4.3　SocketChannel

		- 在NIO中，涉及网络连接的通道有两个：一个是SocketChannel，负责连接的数据传输；另一个是ServerSocketChannel，负责连接的监听。其中，NIO中的SocketChannel传输通道与OIO中的Socket类对应，NIO中的ServerSocketChannel监听通道对应于OIO中的ServerSocket类。
		- 1. 获取SocketChannel传输通道

			- 在客户端，先通过SocketChannel静态方法open()获得一个套接字传输通道，然后将socket设置为非阻塞模式，最后通过connect()实例方法对服务器的IP和端口发起连接。

		- 2. 读取SocketChannel传输通道
		- 3. 写入SocketChannel传输通道
		- 4. 关闭SocketChannel传输通道

	- 3.4.4　使用SocketChannel发送文件的实战案例
	- 3.4.5　DatagramChannel

		- 使用UDP时，只要知道服务器的IP和端口就可以直接向对方发送数据。在Java NIO中，使用DatagramChannel来处理UDP的数据传输。
		- 1. 获取DatagramChannel
		- 2. 从DatagramChannel读取数据
		- 3. 写入DatagramChannel
		- 4. 关闭DatagramChannel

	- 3.4.6　使用DatagramChannel发送数据的实战案例
	- 3.5　详解NIO Selector

		- 3.5.1　选择器与注册

			- 简单地说，选择器的使命是完成IO的多路复用，其主要工作是通道的注册、监听、事件查询。一个通道代表一条连接通路，通过选择器可以同时监控多个通道的IO（输入输出）状况。选择器和通道的关系是监控和被监控的关系。
			- 在NIO编程中，一般是一个单线程处理一个选择器，一个选择器可以监控很多通道。所以，通过选择器，一个单线程可以处理数百、数千、数万甚至更多的通道
			- 可供选择器监控的通道IO事件类型

				- （1）可读：SelectionKey.OP_READ。
				- （2）可写：SelectionKey.OP_WRITE。
				- （3）连接：SelectionKey.OP_CONNECT。
				- （4）接收：SelectionKey.OP_ACCEPT。
				- 如果选择器要监控通道的多种事件，可以用“按位或”运算符来实现。

			- 什么是IO事件？

				- 这里的IO事件不是对通道的IO操作，而是通道处于某个IO操作的就绪状态，表示通道具备执行某个IO操作的条件

		- 3.5.2　SelectableChannel

			- 一个通道若能被选择，则必须继承SelectableChannel类

		- 3.5.3　SelectionKey

			- 通道和选择器的监控关系注册成功后就可以选择就绪事件，具体的选择工作可调用Selector的select()方法来完成。

		- 3.5.4　选择器使用流程

			- （1）获取选择器实例。选择器实例是通过调用静态工厂方法open()来获取的
			- （2）将通道注册到选择器实例
			- （3）选出感兴趣的IO就绪事件（选择键集合）
			- 用于选择就绪的IO事件的select()方法有多个重载的实现版本

				- （1）select()：阻塞调用，直到至少有一个通道发生了注册的IO事件。
				- （2）select(long timeout)：和select()一样，但最长阻塞时间为timeout指定的毫秒数。
				- （3）selectNow()：非阻塞，不管有没有IO事件都会立刻返回。

	- 3.5.5　使用NIO实现Discard服务器的实战案例

		- 仅读取客户端通道的输入数据，读取完成后直接关闭客户端通道，并且直接抛弃掉（Discard）读取到的数据

	- 3.5.6　使用SocketChannel在服务端接收文件的实战案例

		- 客户端每次传输文件都会分为多次传输：首先传入文件名称，其次是文件大小，然后是文件内容

- 3.5　详解NIO Selector

  Java NIO的三大核心组件是Channel（通道）、Buffer（缓冲区）、Selector（选择器）。其中，通道和缓冲区的联系比较密切：数据总是从通道读到缓冲区内，或者从缓冲区写入通道中
  

	- 3.5.1　选择器与注册
	- 3.5.2　SelectableChannel

## 第4章 Reactor模型

### 4.3　多线程Reactor模式

- 4.3.1　多线程版本的Reactor模式演进

	- （1）升级Handler。既要使用多线程，又要尽可能高效率，则可以考虑使用线程池。
	- （2）升级Reactor。可以考虑引入多个Selector（选择器），提升选择大量通道的能力。

- 4.3.2　多线程版本Reactor的实战案例

	- 1. 创建两个子反应堆，负责查询和分发两个选择器的事件

		- 1. 负责查询和分发新连接事件
		- 2. 负责查询和分发IO事件

	- 2. 两个轮训事件线程

		- 1. 负责新事件轮训线程
		- 2. 负责IO事件轮训线程

- 4.3.3　多线程版本Handler的实战案例

	- 主要的升级是引入了一个线程池（ThreadPool），使得数据传输和业务处理的代码执行在独立的线程池中，彻底地做到IO处理以及业务处理线程和反应器IO事件轮询线程的完全隔离

### 4.4. 主从Reactor多线程

### 4.4　Reactor模式的优缺点

- （1）Reactor模式和生产者消费者模式对比
- （2）Reactor模式和观察者模式对比
- Reactor模式的优点

	- 1. 响应快，虽然同一反应器线程本身是同步的，但是不会被单个连接的IO操作所阻塞。
	- 2. 编程相对简单，最大限度避免了复杂的多线程同步，也避免了多线程各个进程之间切换的开销。
	- 3. 可扩展，可以方便地通过增加反应器线程的个数来充分利用CPU资源。

- Reactor模式的缺点

	- 1. Reactor模式增加了一定的复杂性，因而有一定的门槛，并且不易于调试。
	- 2. Reactor模式依赖于操作系统底层的IO多路复用系统调用的支持，如Linux中的epoll系统调用。如果操作系统的底层不支持IO多路复用，Reactor模式不会那么高效。
	- 3. 在同一个Handler业务线程中，如果出现一个长时间的数据读写，就会影响这个反应器中其他通道的IO处理。例如，在大文件传输时，IO操作就会影响其他客户端的响应时间。对于这种操作，还需要进一步对Reactor模式进行改进。

### Netty模型

## 第5章 Netty核心原理与基础实战

Netty是一个Java NIO客户端/服务器框架，是一个为了快速开发可维护的高性能、高可扩展的网络服务器和客户端程序而提供的异步事件驱动基础框架和工具


### 5.1　第一个Netty实战案例DiscardServer

- 5.1.1　创建第一个Netty项目

	- 读取客户端的输入数据，直接丢弃，不给客户端任何回复

- 5.1.2　第一个Netty服务端程序
- 5.1.3　业务处理器NettyDiscardHandler
- 5.1.4　运行NettyDiscardServer

	- 服务器引导类、缓冲区、反应器、业务处理器、Future异步回调、数据传输通道等

### 5.2　解密Netty中的Reactor模式

- 5.2.1　回顾Reactor模式中IO事件的处理流程

	- 第1步：通道注册。IO事件源于通道（Channel），IO是和通道（对应于底层连接而言）强相关的。一个IO事件一定属于某个通道。如果要查询通道的事件，首先就要将通道注册到选择器。
	- 第2步：查询事件。在Reactor模式中，一个线程会负责一个反应器（或者SubReactor子反应器），不断地轮询，查询选择器中的IO事件（选择键）。
	- 第3步：事件分发。如果查询到IO事件，则分发给与IO事件有绑定关系的Handler业务处理器。
	- 第4步：完成真正的IO操作和业务处理，这一步由Handler业务处理器负责。

- 5.2.2　Netty中的Channel
- 5.2.3　Netty中的Reactor
- 5.2.4　Netty中的Handler

	- 通道IO事件类型

		- 可读：SelectionKey.OP_READ。
		- 可写：SelectionKey.OP_WRITE。
		- 连接：SelectionKey.OP_CONNECT。
		- 接收：SelectionKey.OP_ACCEPT。

	- Netty的Handler分为两大类：第一类是ChannelInboundHandler入站处理器；第二类是ChannelOutboundHandler出站处理器，二者都继承了ChannelHandler处理器接口
	- 在通道中发生了OP_READ事件后，会被EventLoop查询到，然后分发给ChannelInboundHandler入站处理器，调用对应的入站处理的read()方法。在ChannelInboundHandler入站处理器内部的read()方法具体实现中，可以从通道中读取数据。
	- 在应用程序完成业务处理后，可以通过ChannelOutboundHandler出站处理器将处理的结果写入底层通道。

- 5.2.5　Netty中的Pipeline

	- Netty的Reactor模式实现中各个组件之间的关系

		- （1）反应器（或者SubReactor子反应器）和通道之间是一对多的关系：一个反应器可以查询很多个通道的IO事件。
		- （2）通道和Handler处理器实例之间是多对多的关系：一个通道的IO事件可以被多个Handler实例处理；一个Handler处理器实例也能绑定到很多通道，处理多个通道的IO事件。

### 5.3　详解Bootstrap

Bootstrap类是Netty提供的一个便利的工厂类，可以通过它来完成Netty的客户端或服务端的Netty组件的组装，以及Netty程序的初始化和启动执行。Netty的官方解释是，完全可以不用这个Bootstrap类，可以一点点去手动创建通道、完成各种设置和启动注册到EventLoop反应器，然后开始事件的轮询和处理，但是这个过程会非常麻烦。通常情况下，使用这个便利的Bootstrap工具类的效率会更高。


- 5.3.1　父子通道
- 5.3.2　EventLoopGroup
- 5.3.3　Bootstrap启动流程

	- Bootstrap的启动流程也就是Netty组件的组装、配置，以及Netty服务器或者客户端的启动流程。在本节中对启动流程进行了梳理，大致分成8个步骤。

		- 第1步：创建反应器轮询组，并设置到ServerBootstrap引导类实例
		- 第2步：设置通道的IO类型。
		- 第3步：设置监听端口
		- 第4步：设置传输通道的配置选项
		- 第5步：装配子通道的Pipeline。

			- 每一个通道都用一条ChannelPipeline流水线，它的内部有一个双向的链表。装配流水线的方式是：将业务处理器ChannelHandler实例包装之后加入双向链表中。

		- 第6步：开始绑定服务器新连接的监听端口
		- 第7步：自我阻塞，直到监听通道关闭
		- 第8步：关闭EventLoopGroup

- 5.3.4　ChannelOption

	- 1. SO_RCVBUF和SO_SNDBUF

		- 设置TCP连接的两个缓冲区大小的

	- 2. TCP_NODELAY

		- 此为TCP传输选项，如果设置为true就表示立即发送数据

	- 3. SO_KEEPALIVE

		- 此为TCP传输选项，表示是否开启TCP的心跳机制

	- 4. SO_REUSEADDR

		- 此为TCP传输选项，为true时表示地址复用，默认值为false

	- 5. SO_LINGER

		- 此为TCP传输选项，可以用来控制socket.close()方法被调用后的行为，包括延迟关闭时间

	- 6. SO_BACKLOG

		- 此为TCP传输选项，表示服务端接收连接的队列长度，如果队列已满，客户端连接将被拒绝

	- 7. SO_BROADCAST

		- 此为TCP传输选项，表示设置为广播模式。

### 5.4　详解Channel

- 5.4.1　Channel的主要成员和方法

	- 通道接口中所定义的几个重要方法

		- （1）ChannelFuture connect(SocketAddress address)
		- （2）ChannelFuture bind(SocketAddress address)
		- （3）ChannelFuture close()
		- （4）Channel read()
		- （5）ChannelFuture write（Object o）
		- （6）Channel fush()

- 5.4.2　EmbeddedChannel

### 5.5　详解Handler

整个IO处理操作环节大致包括从通道读数据包、数据包解码、业务处理、目标数据编码、把数据包写到通道，然后由通道发送到对端


- 5.5.1　ChannelInboundHandler入站处理器

	- 当对端数据入站到Netty通道时，Netty将触发ChannelInboundHandler入站处理器所对应的入站API，进行入站操作处理
	- 对于ChannelInboundHandler的核心方法

- 5.5.2　ChannelOutboundHandler出站处理器
- 5.5.3　ChannelInitializer通道初始化处理器
- 5.5.4　ChannelInboundHandler的生命周期的实战案例

### 5.6　详解Pipeline

前面讲到，一条Netty通道需要很多业务处理器来处理业务。每条通道内部都有一条流水线（Pipeline）将Handler装配起来。Netty的业务处理器流水线ChannelPipeline是基于责任链设计模式（Chain of Responsibility）来设计的，内部是一个双向链表结构，能够支持动态地添加和删除业务处理器。


- 5.6.1　Pipeline入站处理流程
- 5.6.2　Pipeline出站处理流程
- 5.6.3　ChannelHandlerContext

	- 流水线ChannelPipeline中的双向链接实质是一个由ChannelHandlerContext组成的双向链表。作为Context的成员，无状态的Handler关联在ChannelHandlerContext中。
	- Channel、Handler、ChannelHandlerContext三者的关系

		- Channel拥有一条ChannelPipeline，每一个流水线节点为一个ChannelHandlerContext上下文对象，每一个上下文中包裹了一个ChannelHandler。在ChannelHandler的入站/出站处理方法中，Netty会传递一个Context实例作为实际参数。处理器中的回调代码可以通过Context实参，在业务处理过程中去获取ChannelPipeline实例或者Channel实例。

- 5.6.4　HeadContext与TailContext

	- 一个头部上下文HeadContext，一个尾部上下文TailContext。pipeline的创建、初始化除了保存一些必要的属性外，核心就在于创建了HeadContext头节点和TailContext尾节点。

- 5.6.5　Pipeline入站和出站的双向链接操作

	- 在理解了HeadContext与TailContext两个重要的节点之后，再来梳理一下Pipeline的出站和入站处理流程中的双向链接操作。

- 5.6.6　截断流水线的入站处理传播过程
- 5.6.7　在流水线上热插拔Handler

	- Netty中的处理器流水线是一个双向链表。在程序执行过程中，可以动态进行业务处理器的热插拔：动态地增加、删除流水线上的业务处理器。

### 5.7 详解ByteBuf

- 5.7.1　ByteBuf的优势

	- Pooling（池化）
	- 减少了内存复制和GC，提升了效率,]
	- 复合缓冲区类型，支持零复制。
	- 不需要调用fip()方法去切换读/写模式。
	- 可扩展性好。
	- 可以自定义缓冲区类型。
	- 读取和写入索引分开。
	- 方法的链式调用。可以进行引用计数，方便重复使用。

- 5.7.2　ByteBuf的组成部分

	- ByteBuf是一个字节容器，内部是一个字节数组

- 5.7.3 ByteBuf的重要属性

	- readerIndex（读指针）
	- writerIndex（写指针）
	- maxCapacity（最大容量）

- 5.7.4　ByteBuf的方法

	- 第一组：容量系列

		- capacity()：表示ByteBuf的容量，是废弃的字节数、可读字节数和可写字节数之和。
		- maxCapacity()：表示ByteBuf能够容纳的最大字节数。当向ByteBuf中写数据的时候，如果发现容量不足，则进行扩容，直至扩容到maxCapacity设定的上限。

	- 第二组：写入系列

		- isWritable()

			- 表示ByteBuf是否可写。如果capacity()容量大于writerIndex指针的位置，则表示可写，否则为不可写。注意：isWritable()返回false并不代表不能再往ByteBuf中写数据了。如果Netty发现往ByteBuf中写数据写不进去，就会自动扩容ByteBuf。

	- 第三组：读取系列

- 5.7.5　ByteBuf基本使用的实战案例

	- （1）分配一个ByteBuf实例。
	- （2）向ByteBuf写数据。
	- （3）从ByteBuf读数据。

- 5.7.6　ByteBuf的引用计数

	- Netty之所以采用“计数器”来追踪ByteBuf的生命周期，一是能对Pooled ByteBuf进行支持，二是能够尽快“发现”那些可以回收的ByteBuf（非Pooled），以便提升ByteBuf的分配和销毁的效率
	- Buffer缓冲区实例会被频繁创建、使用、释放，从而频繁创建对象、内存分配、释放内存，这样会导致系统的开销大、性能低

- 5.7.7　ByteBuf的分配器

	- Netty通过ByteBufAllocator分配器来创建缓冲区和分配内存空间

- 5.7.8　ByteBuf缓冲区的类型

	- 根据内存的管理方不同，缓冲区分为堆缓冲区和直接缓冲区，也就是Heap ByteBuf和Direct ByteBuf

- 5.7.9　两类ByteBuf使用的实战案例
- 5.7.10　ByteBuf的自动创建与自动释放

	- 1. ByteBuf的自动创建

		- 1. 在入站处理时，Netty是何时自动创建入站的ByteBuf缓冲区的呢？
		- 2. 分配缓冲区的时候，为什么要计算大小呢？
		- 3. 在入站处理完成时，入站的ByteBuf是如何自动释放的呢？

			- 方式一：TailContext自动释放

				- 1. 如何让ByteBuf数据包通过流水线一路向后传递，到达末尾的TailContext呢？

			- 方式二：SimpleChannelInboundHandler自动释放

	- 2. 出站处理时的自动释放

- 5.7.11　ByteBuf浅层复制的高级使用方式

	- 1. 切片浅层复制
	- 2. 整体浅层复制
	- 3. 浅层复制的问题

### 5.8　Netty的零拷贝

- 5.8.1　通过CompositeByteBuf实现零拷贝
- 5.8.2　通过wrap操作实现零拷贝

### 5.9　EchoServer的实战案例

- 5.9.1　NettyEchoServer
- 5.9.2　NettyEchoServerHandler
- 5.9.3　NettyEchoClient
- 5.9.4　NettyEchoClientHandler

## 第6章　Decoder与Encoder核心组件

### 6.1　Decoder原理与实战

- 6.1.1　ByteToMessageDecoder解码器处理流程
- 6.1.2　自定义Byte2IntegerDecoder整数解码器
- 6.1.3　ReplayingDecoder解码器
- 6.1.4　整数的分包解码器的实战案例
- 6.1.5　字符串的分包解码器的实战案例
- 6.1.6　MessageToMessageDecoder解码器

### 6.2　常用的内置Decoder

- 概述

	- （1）固定长度数据包解码器——FixedLengthFrameDecoder

		- 每个接收到的数据包的长度都是固定的

	- （2）行分割数据包解码器——LineBasedFrameDecoder

		- 换行符（或者回车换行符）作为边界分隔符

	- （3）自定义分隔符数据包解码器——DelimiterBasedFrameDecoder

		- 可以自定义分隔符
		- 末尾必须带上对应的分隔符

	- （4）自定义长度数据包解码器——LengthFieldBasedFrameDecoder

		- 长度字段
		- 解码时会按照原始数据包长度进行提取

- 6.2.1　LineBasedFrameDecoder解码器

	- 1. 工作原理

		- 依次遍历ByteBuf数据包中的可读字节，判断在二进制字节流中是否存在换行符"\n"或者"\r\n"的字节码。如果有，就以此位置为结束位置，把从可读索引到结束位置之间的字节作为解码成功后的ByteBuf数据包

	- 2. 最大长度值

		- 如果连续读取到最大长度后仍然没有发现换行符，就会抛出异常

- 6.2.2　DelimiterBasedFrameDecoder解码器

	- 还可以使用其他特殊字符作为数据包的分隔符，例如制表符"\t"

- 6.2.3　LengthFieldBasedFrameDecoder解码器

	- LengthFieldBasedFrameDecoder构造器

		- （1）maxFrameLength：发送的数据包的最大长度。示例程序中该值为1024，表示一个数据包最多可发送1024字节。
		- （2）lengthFieldOffset：长度字段偏移量，指的是长度字段位于整个数据包内部字节数组中的下标索引值。
		- （3）lengthFieldLength：长度字段所占的字节数。如果长度字段是一个int整数，则为4；如果长度字段是一个short整数，则为2。
		- （4）lengthAdjustment：长度的调整值。这个参数最为难懂。在传输协议比较复杂的情况下，例如协议包含了长度字段、协议版本号、魔数等，那么解码时就需要进行长度调整。长度调整值的计算公式为：内容字段偏移量-长度字段偏移量-长度字段的字节数。这个公式一看就比较复杂，下一小节会有详细的举例说明。

			- 长度调整值的计算公式为：内容字段偏移量-长度字段偏移量-长度字段的字节数。

		- （5）initialBytesToStrip：丢弃的起始字节数。在有效数据字段Content前面，如果还有一些其他字段的字节，作为最终的解析结果可以丢弃。例如，在上面的示例程序中，前面有4字节的长度字段，它起辅助的作用，最终的结果中不需要这个长度，所以丢弃的字节数为4。

- 6.2.4　多字段Head-Content协议数据包解析的实战案例

	- 1. 版本号的长度
	- 2. 将2字节的协议版本放在最前面，在长度字段前面加上2字节的版本字段，在长度字段后面加上4字节的魔数，魔数用来对数据包做一些安全的认证

### 6.3　Encoder原理与实战

- 6.3.1　MessageToByteEncoder编码器

	- MessageToByteEncoder的功能是将一个Java POJO对象编码成一个ByteBuf数据包

- 6.3.2　MessageToMessageEncoder编码器

	- 完成原POJO类型到目标POJO类型的转换逻辑

### 6.4　解码器和编码器的结合

- 6.4.1　ByteToMessageCodec编解码器
- 6.4.2　CombinedChannelDuplexHandler组合器

## 第7章　序列化与反序列化：JSON和Protobuf

### 概述

- 我们在开发一些远程过程调用（RPC）的程序时通常会涉及对象的序列化/反序列化问题，例如一个Person对象从客户端通过TCP方式发送到服务端
- JSON的可读性较强。这种方式的缺点是它的性能稍差
- 如何选择序列化/反序列化框架呢？

	- （1）结果数据大小：原则上说，序列化后的数据尺寸越小，传输效率越高。
	- （2）结构复杂度：会影响序列化/反序列化的效率，结构越复杂越耗时。
	- 对于对性能要求不是太高的服务器程序，可以选择JSON文本格式的序列化框架；对于性能要求比较高的服务器程序，应该选择传输效率更高的二进制序列化框架，建议是Protobuf。

- Netty也提供了相应的编解码器，为Protobuf解决了有关Socket通信中“半包、粘包”等问题。

### 7.1　详解粘包和拆包

- 7.1.1　半包问题的实战案例
- 7.1.2　什么是半包问题
- 7.1.3　半包问题的根因分析

### 7.2　使用JSON协议通信

- 7.2.1　JSON的核心优势
- 7.2.2　JSON序列化与反序列化开源库
- 7.2.3　JSON序列化与反序列化的实战案例
- 7.2.4　JSON传输的编码器和解码器
- 7.2.5　JSON传输的服务端的实战案例
- 7.2.6　JSON传输的客户端的实战案例

### 7.3　使用Protobuf协议通信

- 7.3.1　一个简单的proto文件的实战案例

	- 1. 在.proto文件的头部声明中，需要声明一下所使用的Protobuf协议版本
	- 2. option java_package选项的作用

		- 生成proto文件中消息的POJO类和Builder（构造者）的Java代码时，将生成的Java代码放入该选项所指定的package类路径中

	- 3. option java_outer_classname

		- 在生成proto文件所对应的Java代码时，生成的Java外部类使用配置的名称

	- 4. 使用message关键字来定义消息的结构体
	- 5. 每个消息结构体可以有多个字段

		- 定义一个字段的格式为“类型名称 = 编号”

	- 6. 在一个proto文件中可以声明多个message，.分情况下会把存在依赖关系或者包含关系的message结构体写入一个proto文件，将那些没有关系、相互独立的message结构体分别写入不同的文件，这样便于管理。

- 7.3.2　通过控制台命令生成POJO和Builder

	- protoc --java_out=NettyDemos/src/main/java/ NettyDemos/protobuf/Msg1.proto

- 7.3.3　通过Maven插件生成POJO和Builder
- 7.3.4　Protobuf序列化与反序列化的实战案例

### 7.4　Protobuf编解码的实战案例

- 7.4.1　Netty内置的Protobuf基础编码器/解码器
- 7.4.2　Protobuf传输的服务端的实战案例
- 7.4.3　Protobuf传输的客户端的实战案例

### 7.5　详解Protobuf协议语法

- 7.5.1　proto文件的头部声明
- 7.5.2　Protobuf的消息结构体与消息字段
- 7.5.3　Protobuf字段的数据类型
- 7.5.4　proto文件的其他语法规范

## 第8章　基于Netty单体IM系统的开发实战

### 8.1　自定义Protobuf编解码器

- 8.1.1　自定义Protobuf编码器
- 8.1.2　自定义Protobuf解码器
- 8.1.3　IM系统中Protobuf消息格式的设计

### 8.2　IM的登录流程

- 8.2.1　图解登录/响应流程的环节
- 8.2.2　客户端涉及的主要模块
- 8.2.3　服务端涉及的主要模块

### 8.3　客户端的登录处理的实战案例

- 8.3.1　LoginConsoleCommand和User POJO
- 8.3.2　LoginSender
- 8.3.3　ClientSession
- 8.3.4　LoginResponseHandler
- 8.3.5　客户端流水线的装配

### 8.4　服务端的登录响应的实战案例

- 8.4.1　服务端流水线的装配
- 8.4.2　LoginRequestHandler
- 8.4.3　LoginProcesser
- 8.4.4　EventLoop线程和业务线程相互隔离

### 8.5　详解Session服务器会话

- 8.5.1　通道的容器属性
- 8.5.2　ServerSession服务端会话类

### 8.6　点对点单聊的实战案例

- 8.6.1　单聊的端到端流程
- 8.6.2　客户端的ChatConsoleCommand收集聊天内容
- 8.6.3　客户端的CommandController发送POJO
- 8.6.4　服务端的ChatRedirectHandler进行消息转发
- 8.6.5　服务端的ChatRedirectProcesser进行异步消息转发
- 8.6.6　客户端的ChatMsgHandler聊天消息处理器

### 8.7　详解心跳检测

- 8.7.1　网络连接的假死现象
- 8.7.2　服务端的空闲检测
- 8.7.3　客户端的心跳发送

## 第9章　HTTP原理与Web服务器实战

### 9.1　高性能Web应用架构

- 9.1.1　十万级并发的Web应用架构
- 9.1.2　千万级高并发的Web应用架构

### 9.2　详解HTTP应用层协议

- 9.2.1　HTTP简介
- 9.2.2　HTTP的请求URL
- 9.2.3　HTTP的请求报文
- 9.2.4　HTTP的响应报文
- 9.2.5　HTTP中GET和POST的区别

### 9.3　HTTP的演进

- 9.3.1　HTTP的1.0版本
- 9.3.2　HTTP的1.1版本
- 9.3.3　HTTP的2.0版本

### 9.4　基于Netty实现简单的Web服务器

- 9.4.1　基于Netty的HTTP服务器演示实例
- 9.4.2　基于Netty的HTTP请求的处理流程
- 9.4.3　Netty内置的HTTP报文解码流程
- 9.4.4　基于Netty的HTTP响应编码流程
- 9.4.5　HttpEchoHandler回显业务处理器的实战案例
- 9.4.6　使用Postman发送多种类型的请求体

## 第10章　高并发HTTP通信的核心原理

### 10.1　需要进行HTTP连接复用的高并发场景

- 10.1.1　反向代理Nginx与Java Web应用服务之间的HTTP高并发通信
- 10.1.2　微服务网关与微服务Provider实例之间的HTTP高并发通信
- 10.1.3　分布式微服务Provider实例之间的RPC的HTTP高并发通信
- 10.1.4　Java通过HTTP客户端访问REST接口服务的HTTP高并发通信

### 10.2　详解传输层TCP

- 10.2.1　TCP/IP的分层模型

## 第15章 亿级高并发IM架构与实战

本章结合分布式缓存Redis、分布式协调ZooKeeper、高性能通信Netty，从架构的维度设计一套亿级IM的高并发架构方案


### 15.1　支撑亿级流量的高并发IM架构的理论基础

- 15.1.1　亿级流量的系统架构的开发实战

	- （1）Netty服务集群：主要用来负责维持和客户端的TCP连接，完成消息的发送和转发。
	- （2）ZooKeeper集群：负责Netty Server集群的管理，包括注册、路由、负载均衡，集群IP注册和节点ID分配，主要在于为ZooKeeper集群提供底层服务。
	- （3）Redis集群：负责用户、用户绑定关系、用户群组关系、用户远程会话等数据的缓存，缓存其他的配置数据或者临时数据，加快读取速度。
	- （4）MySQL集群：保存用户、群组、离线消息等。
	- （5）RocketMQ消息队列集群：主要是将优先级不高的操作从高并发模式转成低并发模式。例如，将离线消息发向消息队列，然后通过低并发的异步任务保存到数据库。

- 15.1.2　高并发架构的技术选型

	- （1）核心组件：Netty4.x + Spring4.x + ZooKeeper 3.x +Redis 3.x + RocketMQ 3.x+ MySQL 5.x+ Mongogo 3.x + Spring Cloud Finchley+ Nginx 15。
	- （2）短连接服务：Spring Cloud。基于RESTful短连接的分布式微服务架构，完成用户在线管理、单点登录系统。
	- （3）长连接服务：Netty，主要用来负责维持和客户端的TCP连接，完成消息的发送和转发。
	- （4）消息队列：RocketMQ高速消队列。
	- （5）数据库：MySQL + MongoDB。MySQL用来存储结构化数据，如用户数据；MongoDB用来存储半结构化的离线消息。
	- （6）序列化协议：Protobuf + JSON。Protobuf是最高效的二进制序列化协议，用于长连接；JSON是最紧凑的文本协议，用于短连接。

- 15.1.3　详解IM消息的序列化协议选型

	- 1. 对于并发度不高的IM系统，使用文本协议，例如JSON
	- 2. 对于并发度非常高，QPS在千万级、亿级的通信系统，尽量选择二进制协议

- 15.1.4　详解长连接和短连接

	- 短连接适用于数据请求频度较低的应用场景，例如网站的浏览和普通的Web请求。短连接的优点是管理起来比较简单，存在的连接都是有用的连接，不需要额外的控制手段。
	- 短连接服务器也叫Web服务，主要功能是实现用户的登录鉴权和拉取好友、群组、数据档案等相对低频的请求操作。
	- 长连接服务器也叫IM服务，主要作用是用来和客户端建立并维持长连接，实现消息的传递和即时转发。分布式网络非常复杂，长连接管理是重中之重，需要考虑到连接保活、连接检测、自动重连等方方面面的工作。
	- 在分布式集群的环境下，用户首先通过短连接登录Web服务器。Web服务器在完成用户的账号/密码验证、返回UID和令牌（Token）时，还需要通过一定策略获取目标IM服务器的IP地址和端口号列表，并返回给客户端。客户端开始连接IM服务器，连接成功后，发送鉴权请求，鉴权成功则授权的长连接正式建立。

### 15.2　分布式IM的命名服务的实战案例

- 15.2.1　IM节点的POJO类

	- 首先定义一个POJO类，保存IM Worker节点的基础信息，如Netty服务IP、服务端口，以及Netty的服务连接数

- 15.2.2　IM节点的ImWorker类

	- 所有的工作节点都在ZooKeeper的同一个父节点下创建顺序节点，然后从返回的临时路径上取得属于自己的后缀编号
	- 这里有三个ZNode相关的路径：MANAGE_PATH、pathPrefix和pathRegistered

		- 第一个MANAGE_PATH是一个常量，值为"/im/nodes"，为所有Worker临时工作节点的父亲节点的路径，在创建Worker节点之前，首先要检查一下父亲ZNode节点是否存在，否则先创建父亲节点。"/im/nodes"父亲节点的创建方式是，持久化节点，而不是临时节点。
		- 第二路径pathPrefix是所有临时节点的前缀，值为"/im/nodes/"，是在工作路径后加上一个“/”分隔符；也可在工作路径的后面加上其他的前缀字符，如"/im/nodes/id-"、"/im/nodes/seq-"等。
		- 第三路径pathRegistered是临时节点创建成功之后返回的完整路径，例如/im/nodes/0000000000、/im/nodes/0000000001等。后边的编号是顺序的。

### 15.3　Worker集群的负载均衡的实战案例

在高并发的IM系统中，负载均衡需要将IM长连接分摊到不同的Netty服务器，防止单个Netty服务器负载过大，从而导致其不可用。


- 15.3.1　ImLoadBalance负载均衡器

	- 短连接网关WebGate需要通过查询ZooKeeper集群来获得最佳的Netty服务器。定义一个负载均衡器ImLoadBalance类，将计算最佳Netty服务器的算法放在负载均衡器中
	- 短连接网关WebGate会调用getBestWorker()方法取得最佳的IM服务器。

		- 一个是取得所有的IM服务器列表，注意是带负载的；另一个是通过负载信息计算最小负载的服务器。
		- 在balance()方法中通过一个简单的排序算法计算出balance值最小的ImNode对象。

- 15.3.2　与WebGate的整合

	- 短连接网关WebGate登录成功之后，需要通过负载均衡器ImLoadBalance类查询到最佳的Netty服务器，并且返回给客户端

### 15.4　即时通信消息的路由和转发的实战案例

1. 如果连接在不同的Netty Worker工作站点的客户端之间需要相互进行消息的发送，就需要在不同的Worker节点之间进行路由和转发
2. 由于节点和节点之间都有可能需要相互转发，因此节点之间的连接是一种网状结构。每一个节点都需要具备路由的能力。


- 15.4.1　IM路由器WorkerRouter

	- 为每一个Worker节点增加一个IM路由器类，名为WorkerRouter

		- 1. 一是要订阅到集群中所有的在线Netty服务器
		- 2. 二是要其他的Netty服务器建立一个长连接，用于转发消息。

	- WorkerRouter路由器使用Curator的TreeCache缓存订阅了节点的NODE_ADDED节点添加消息。当一个新的Netty节点加入时，调用processNodeAdded(data)方法在本地保存一份节点的POJO信息，并且建立一个消息中转的Netty客户连接。
	- WorkerRouter路由器有一个容器成员workerMap，用于封装和保存所有的在线节点
	- 因为WorkerRouter路由器的主要作用除了路由节点，还需要进行消息的转发，所以WorkerRouter路由器保存的是转发器PeerSender，而添加的远程Netty节点的POJO信息被封装在转发器中

- 15.4.2　IM转发器PeerSender

	- IM转发器PeerSender封装了远程节点的IP地址、端口号以及ID
	- 它是一个Netty的NIO客户端，维护了一个到远程节点的Netty通道，通过这个通道将消息转发给远程的节点
	- 转发器有一个消息转发的方法，直接通过Netty通道将消息发送到远程节点

### 15.5　在线用户统计的实战案例

利用ZooKeeper可以实现一个集群共享的计数器，只要使用相同的path就可以得到最新的计数器值，这是由ZooKeeper的一致性保证的。


- 15.5.1　Curator的分布式计数器

	- Curator有两个计数器：一个用int类型来计数（SharedCount）
	- 一个用long类型来计数（DistributedAtomicLong）

- 15.5.2　用户上线和下线的统计

*XMind - Trial Version*