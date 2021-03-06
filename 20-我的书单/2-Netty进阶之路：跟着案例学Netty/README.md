# Netty进阶之路：跟着案例学Netty

## 第1章 Netty服务端意外退出案例

在使用 Netty 进行服务端程序开发时，主要涉及端口监听、EventLoop 线程池创建、NioServerSocketChannel 和 ChannelPipeline 初始化等。初学者如果对服务端相关类库的工作原理和用法不熟悉，则会导致程序启动或者退出发生问题。本章从一个初学者容易犯错的案例展开分析，抽丝剥茧，让大家更好地掌握 Netty服务端启停的原理和技巧，避免在工作中犯类似的错误。


### 1.1 Netty服务端意外退出问题

- 1.1.1 Java Daemon线程简介
- 1.1.2 Netty服务端启动原理
- 1.1.3 如何防止Netty服务端意外退出
- 1.1.4 实际项目中的优化策略

### 1.2 Netty优雅退出机制

- 1.2.1 Java优雅退出机制
- 1.2.2 Java优雅退出的注意点

    - （1）ShutdownHook在某些情况下并不会被执行，例如JVM崩溃、无法接收信号量和kill-9 pid等。
    - （2）当存在多个ShutdownHook时，JVM无法保证它们的执行先后顺序。
    - （3）在JVM关闭期间不能动态添加或者去除ShutdownHook。
    - （4）不能在ShutdownHook中调用System.exit（），它会卡住JVM，导致进程无法退出。

- 1.2.3 Netty优雅退出机制

    - 1. Netty也需要优雅退出，主要原因

        - （1）尽快释放NIO线程和句柄等资源。
        - （2）如果使用flush做批量消息发送，需要将积压在发送队列中的待发送消息发送完成。
        - （3）正在写或者读的消息，需要继续处理。
        - （4）设置在NioEventLoop线程调度器中的定时任务，需要执行或清理。

    - 2. Netty优雅退出总结起来有如下三大类操作

        - （1）把 NIO线程的状态位设置成 ST_SHUTTING_DOWN，不再处理新的消息（不允许再对外发送消息）。
        - （2）退出前的预处理操作：把发送队列中尚未发送或者正在发送的消息发送完（备注：不保证能够发送完）、把已经到期或在退出超时之前到期的定时任务执行完成、把用户注册到NIO线程的退出Hook任务执行完成。
        - （3）资源的释放操作：所有Channel的释放、多路复用器的去注册和关闭、所有队列和定时任务的清空取消，最后是EventLoop线程的退出。

- 1.2.4 Netty优雅退出原理和源码分析
- 1.2.5 Netty优雅退出的一些误区

### 1.3 总结

- （1）操作系统的信号量和Java Deamon线程工作机制。
- （2）Netty的NioEventLoop线程工作原理。
- （3）Netty优雅退出相关的几个核心类库。

## 第2章 Netty客户端连接池资源泄漏案例

随着硬件性能的不断提升，多处理器和多网卡已经成为标配，为了充分利用硬件资源，应用程序通过并发编程、客户端和服务端创建多链路的方式提升性能。

通过连接池，客户端和服务端之间可以创建多个 TCP 连接，提升消息的收发能力，同时利用池化技术，可以重用连接，防止反复申请和释放连接，提高连接的使用率。在实际项目中，各类连接池被大量使用，例如数据库连接池、HTTP 连接池等。当使用 Netty客户端创建连接池时，如果对Netty的客户端连接创建机制不熟悉，很可能导致线程膨胀、内存溢出等问题。


### 2.1 Netty连接池资源泄漏问题

- 2.1.1 连接池创建代码
- 2.1.2 内存溢出和线程膨胀
- 2.1.3 错用NIO编程模式
- 2.1.4 正确的连接池创建方式
- 2.1.5 并发安全和资源释放

### 2.2 Netty客户端创建机制

- 2.2.1 Java NIO客户端创建原理分析
- 2.2.2 Netty客户端创建原理分析
- 2.2.3 Bootstrap工具类源码分析

### 2.3 总结

## 第3章 Netty内存池泄漏疑云案例

为了提升消息接收和发送性能，Netty 针对 ByteBuf 的申请和释放采用池化技术，通过PooledByteBufAllocator 可以创建基于内存池分配的 ByteBuf 对象，这样就避免了每次消息读写都申请和释放ByteBuf。由于ByteBuf涉及byte[]数组的创建和销毁，对于性能要求苛刻的系统而言，重用ByteBuf带来的性能收益是非常可观的。

内存池是一把双刃剑，如果使用不当，很容易带来内存泄漏和内存非法引用等问题，另外，除了内存池，Netty同时也支持非池化的ByteBuf，多种类型的ByteBuf功能存在一些差异，使用不当很容易带来各种问题。

本章通过对一个内存池使用不当导致的内存泄漏案例做分析，详细介绍ByteBuf的申请和释放策略，以及Netty 内存池的工作原理。


### 3.1 Netty内存池泄漏问题

- 3.1.1 路由转发服务代码
- 3.1.2 响应消息内存释放玄机
- 3.1.3 采集堆内存快照分析
- 3.1.4 ByteBuf申请和释放的理解误区

### 3.2 Netty内存池工作机制

- 3.2.1 内存池的性能优势
- 3.2.2 内存池工作原理分析
- 3.2.3 内存池核心代码分析

### 3.3 总结

Netty 内存池是一把双刃剑，使用得当会在很大程度上提升系统的性能，但是误用则会带来内存泄漏问题。从表面上看，只要遵循主动申请和释放原则即可，但是由于内存的申请和释放可能由Netty框架隐性完成，增加了内存管理的复杂性。

通过学习 Netty 收发消息的 ByteBuf 申请和释放机制，可以避免在项目中因误用ByteBuf而发生内存泄漏。在熟悉了ByteBuf的申请和释放机制后，通过对Netty内存池工作原理和关键源码的分析，读者可以更好地掌握Netty内存池的使用方法。


## 第4章 ByteBuf故障排查案例

应用在进行数据传输时，往往需要使用缓冲区，最常用的缓冲区就是 JDK NIO 类库提供的java.nio.Buffer。由于 JDK 原生的 Buffer 存在一些缺点，Netty 提供了自己的ByteBuffer实现（ByteBuf类）。

ByteBuf 种类繁多，支持的功能特性存在一些差异，如果使用不当，往往会出现功能异常或者性能问题。本章通过一个业务 ByteBuf的故障排查案例，讲解在 Netty HTTP 协议栈中如何正确地使用ByteBuf，以及ByteBuf的工作原理。


### 4.1 HTTP协议栈ByteBuf使用问题

- 4.1.1 HTTP响应Body获取异常
- 4.1.2 ByteBuf非法引用问题
- 4.1.3 ByteBuf使用注意事项

### 4.2 Netty ByteBuf实现机制

- 4.2.1 Java原生ByteBuffer的局限性
- 4.2.2 Netty ByteBuf工作原理分析
- 4.2.3 ByteBuf引用计数器工作原理和源码分析

### 4.3 总结

ByteBuf 的申请和释放可能会跨 Netty 的 NioEventLoop 和业务线程，跨线程操作ByteBuf 时一定要谨慎，防止发生并发安全和非法引用问题。另外，由于 ByteBuf 的实现类非常多，不同的实现功能特性存在差异，用户在使用时一定要认真阅读 API Doc说明，必要时要看源码，防止误用导致出现功能和性能问题。


## 第5章 Netty发送队列积压导致内存泄漏案例

导致 Netty内存泄漏的原因很多，例如，使用内存池方式创建的对象忘记释放，或者系统处理压力过大导致发送队列积压。

尽管 Netty采用了 NIO非阻塞通信，I/O处理往往不会成为业务瓶颈，但是如果客户端并发压力过大，超过了服务端的处理能力，又没有流控保护，则很容易发生内存泄漏。


### 5.1 Netty发送队列积压案例

- 5.1.1 高并发故障场景
- 5.1.2 内存泄漏原因分析
- 5.1.3 如何防止发送队列积压
- 5.1.4 其他可能导致发送队列积压的因素

### 5.2 Netty消息发送工作机制

- 5.2.1 WriteAndFlushTask原理和源码分析
- 5.2.2 ChannelOutboundBuffer原理和源码分析
- 5.2.3 消息发送源码分析
- 5.2.4 消息发送高低水位控制

### 5.3 总结

## 第6章 API网关高并发压测性能波动案例

当前很多Java版的API网关选择基于Netty构建，例如Netflix开源的Zuul2，API网关负责统一的流量接入、安全控制、鉴权、流控和消息路由转发等，对性能要求非常苛刻，在高并发场景下，如果处理不当，很有可能导致内存泄漏、性能波动等问题。


### 6.1 高并发压测性能波动问题

- 6.1.1 故障场景模拟
- 6.1.2 性能波动原因定位
- 6.1.3 主动内存泄漏定位法
- 6.1.4 网关类产品的优化建议

    - （1）内存按需分配。不要一次性申请较大的内存来保存较小的消息，造成内存空间浪费，引发频繁GC问题。
    - （2）不要频繁地创建和释放对象。这会增加GC的负担，降低系统的吞吐量，可以采用内存池等机制优化内存的申请和释放。
    - （3）减少对象拷贝。对于透传类的消息，尽量把涉及业务逻辑处理的字段放入Header，不要对Body 做解码，直接透传到后端服务即可。这样可以大幅减少内存的申请和对象拷贝，降低内存占用，提升性能。
    - （4）流控机制必不可少。除了客户端并发连接数流控、QPS流控，还需要针对内存占用等指标做流控，防止业务高峰期的OOM。

### 6.2 Netty消息接入内存申请机制

- 6.2.1 消息接入的内存分配原理和源码分析
- 6.2.2 Netty ByteBuf的动态扩容原理和源码分析

### 6.3 总结

对于高并发接入的API网关类产品，需要谨慎处理消息的内存申请和释放，减少不必要的申请（例如透传类场景），同时要防止内存空间的浪费。借鉴Netty请求消息读取的内存申请策略和动态扩容机制，并用在实际项目中，可以得到较大的性能提升。


## 第7章 Netty ChannelHandler并发安全案例

ChannelHandler是Netty中使用最广的接口，Netty提供了大量内置的ChannelHandler实现类，包括编解码、SSL、日志打印和流量整形等。用户通过实现ChannelHandler接口，来接收和发送业务消息，并进行业务逻辑处理。

ChannelHandler的一端是Netty NIO线程，另一端则是业务线程池，在多线程并发场景下理解ChannelHandler的并发安全性很重要，如果使用不当，会产生性能和并发安全问题。


### 7.1 Netty ChannelHandler并发安全问题

- 7.1.1 串行执行的ChannelHandler
- 7.1.2 跨链路共享的ChannelHandler
- 7.1.3 ChannelHandler的并发陷阱

### 7.2 Netty ChannelHandler工作机制

- 7.2.1 职责链ChannelPipeline原理和源码分析
- 7.2.2 用户自定义Event原理和源码分析

### 7.3 总结

ChannelHandler是用户最常用的接口，掌握了ChannelHandler及ChannelPipeline工作原理，就清楚了什么时候该使用共享的ChannelHandler，什么时候该对ChannelHandler做并发保护。无论缺少保护还是过度保护，都会给业务带来副作用，甚至严重的功能或性能问题，因此ChannelHandler的并发安全性是非常重要的。


## 第8章 车联网服务端接收不到车载终端消息案例

得益于高性能、低时延的优势，Netty 被广泛应用于物联网领域，用于海量终端设备的协议接入、消息收发和数据处理。

当服务端出现性能瓶颈或者阻塞时，就会导致终端设备连接超时和掉线，引发各种问题，因此在物联网场景下，一定要防止服务端因为编码不当导致的意外阻塞，进而无法处理终端请求消息。


### 8.1 车联网服务端接收不到车载终端消息问题

- 8.1.1 故障现象
- 8.1.2 故障期线程堆栈快照分析
- 8.1.3 NioEventLoop线程防挂死策略

### 8.2 NioEventLoop线程工作机制

- 8.2.1 I/O读写操作原理和源码分析
- 8.2.2 异步任务执行原理和源码分析
- 8.2.3 定时任务执行原理和源码分析
- 8.2.4 Netty多线程最佳实践

### 8.3 总结

当 Netty服务端接收不到消息时，首先需要检查是客户端没有发送到服务端，还是服务端没有读取消息。导致服务端无法读取消息的原因有很多，常见的包括GC导致的应用线程暂停、服务端的NioEventLoop 线程被意外阻塞等。通过网络 I/O 线程和业务逻辑线程分离，可以实现双方的并行处理，提升系统的可靠性。对于用户而言，在编写代码时，始终需要考虑 NioEventLoop 线程是否会被业务代码阻塞，只有消除所有可能导致的阻塞点，才能保证程序稳定运行。


## 第9章 Netty 3.X版本升级案例

目前 Netty的主流版本有 3 个，分别是 3.X、4.0.X和 4.1.X，绝大多数新业务都会采用4.1.X或者4.0.X进行开发，对于已经使用3.X开发的遗留系统而言，是否主动升级Netty版本是一个两难选择：如果不升级，一些新版本的特性，例如 HTTP/2、MQTT、内存池等将无法使用；如果升级，由于包路径、很多 API 接口类库都需要重构，修改工作量大，稍有不慎就会踩坑。

包路径、API的修改是显式修改，升级之后代码编译就会报错，这类问题相对好解决，但是底层的内存分配策略、线程模型的修改却是隐式的，如果对两个版本的架构差异理解不透彻，就会遭遇性能下降、内存泄漏等诸多问题。本章对从Netty 3.X升级到Netty 4.X遇到的典型问题进行总结和分析，希望对需要进行版本升级的读者有帮助。


### 9.1 Netty 3.X的版本升级背景

- 9.1.1 被迫升级场景
- 9.1.2 升级不当遭遇各种问题

### 9.2 版本升级后数据被篡改问题

- 9.2.1 数据篡改原因分析
- 9.2.2 问题总结

### 9.3 升级后上下文丢失问题

- 9.3.1 上下文丢失原因分析
- 9.3.2 依赖第三方线程模型的思考

### 9.4 升级后应用遭遇性能下降问题

- 9.4.1 性能下降原因分析
- 9.4.2 性能优化建议

### 9.5 Netty线程模型变更分析

- 9.5.1 Netty 3.X版本线程模型
- 9.5.2 Netty 4.X版本线程模型
- 9.5.3 线程模型变化点源码分析
- 9.5.4 线程模型变化总结

### 9.6 总结

就Netty而言，掌握线程模型的重要性不亚于熟练使用它的API和功能。很多时候业务遇到的功能、性能等问题，都是由于缺乏对Netty线程模型和原理的理解导致的。对Netty的版本升级需要从功能、兼容性和性能等多个角度进行综合考虑，切不可只盯着API和功能变更这个“芝麻”，而丢掉了线程模型和性能这个“西瓜”。API的变更会导致编译错误，但是性能下降却隐藏于无形之中，稍不留意就会中招。对于强调快速交付和敏捷开发的互联网类应用，升级的时候尤其要小心，不能功能调通后简单验证就匆忙上线。


## 第10章 Netty并发失效导致性能下降案例

为了提升性能，如果用户实现的ChannelHandler包含复杂或者可能导致同步阻塞的业务逻辑，例如数据库操作、同步的第三方服务调用等，往往需要通过线程池来提升并发处理能力，线程池的添加有两种策略：用户自定义线程池执行业务ChannelHandler，以及通过Netty的EventExecutorGroup机制来并行执行ChannelHandler。


### 10.1 业务ChannelHandler无法并发执行问题

- 10.1.1 服务端并发设计相关代码分析
- 10.1.2 无法并行执行的EventExecutorGroup
- 10.1.3 并行执行优化策略和结果

    - （1）按照真实的业务组网，如果所有客户端的并发连接数（TCP/HTTP链路数）小于业务需要配置的线程数，则建议将请求消息封装成任务，投递到后端业务线程池执行，ChannelHandler不需要处理复杂业务逻辑，也不需要再绑定EventExecutorGroup。
    - （2）如果所有客户端的并发连接数（TCP/HTTP链路数）大于或者等于业务需要配置的线程数，则可以为业务 ChannelHandler 绑定 EventExecutorGroup，并在业务ChannelHandler中执行各种业务逻辑（包含 DB访问等可能导致阻塞或者增加耗时的复杂业务逻辑操作）。

### 10.2 Netty DefaultEventExecutor工作机制

DefaultEventExecutor是SingleThreadEventExecutor的一个实现子类，与其对应的还有NioEventLoop类。DefaultEventExecutor实际是一个典型的Event Reactive Thread实现，各种任务被加入任务队列，由一个工作线程循环执行。它与 NioEventLoop类的主要差别是，一个侧重于处理网络I/O相关的各种事件，例如连接操作、消息读取和发送操作，另一个侧重于处理业务相关的逻辑操作，例如将业务的ChannelHandler与具体的DefaultEventExecutor绑定起来，由DefaultEventExecutor 异步执行业务逻辑，实现网络 I/O 线程与业务逻辑处理线程的分离。


- 10.2.1 DefaultEventExecutor原理和源码分析
- 10.2.2 业务线程池优化策略
- 10.2.3 Netty线程绑定机制原理和源码分析

### 10.3 总结

Netty 框架本身实现了高性能的网络读写操作，但是后端业务逻辑执行却是影响性能的关键要素，如果直接将复杂的业务逻辑操作放在I/O线程中完成，一些同步阻塞操作可能会导致I/O线程被阻塞。当把业务逻辑单独拆分到业务线程池中进行处理，与I/O线程隔离时，不同的业务线程模型对性能的影响也非常大。Netty 提供了默认的并行调度ChannelHandler的能力，但是如果使用不当，也会带来性能问题。对于业务自定义实现的线程池，如果追求更高的性能，就需要在消除或者减轻锁竞争上下工夫，线程绑定技术是一个不错的选择，但是也需要根据业务实际场景来实现，例如 TCP 长连接就可以使用ChannelId做Key，如果是短连接，客户端的端口是随机变化的，则不适合使用ChannelId。


## 第11章 IoT百万长连接性能调优案例

随着车联网、智能家居、智慧城市等业务的发展，IoT 进入了飞速发展期。由于要接入海量的硬件设备和传感器，且协议多样化，同时还要在极短的时间内处理大量的数据，所以对服务端的协议接入和处理能力要求极高。

由于 Netty 内置了多种协议栈（TCP、HTTP、MQTT 等），同时利用它提供的编解码框架可以快速地完成私有协议的接入，因此 Netty在 IoT领域得到了较广泛的应用。基于Netty 构建的物联网服务端，实现更多设备的接入和数据收发，提升性能，是一个巨大的挑战。


### 11.1 海量长连接接入面临的挑战

当客户端的并发连接数达到数十万或者数百万时，系统一个较小的抖动就会导致很严重的后果，例如服务端的 GC，导致应用暂停（STW）的 GC 持续几秒，就会导致海量的客户端设备掉线或者消息积压，一旦系统恢复，会有海量的设备接入或者海量的数据发送，很可能瞬间就把服务端冲垮。


- 11.1.1 IoT设备接入特点
- 11.1.2 IoT服务端性能优化场景
- 11.1.3 服务端面临的性能挑战

### 11.2 智能家居内存泄漏问题

- 11.2.1 服务端内存泄漏原因定位
- 11.2.2 问题背后的一些思考

### 11.3 操作系统参数调优

- 11.3.1 文件描述符
- 11.3.2 TCP/IP相关参数
- 11.3.3 多网卡队列和软中断

### 11.4 Netty性能调优

- 11.4.1 设置合理的线程数
- 11.4.2 心跳优化
- 11.4.3 接收和发送缓冲区调优
- 11.4.4 合理使用内存池
- 11.4.5 防止I/O线程被意外阻塞
- 11.4.6 I/O线程和业务线程分离
- 11.4.7 针对端侧并发连接数的流控

### 11.5 JVM相关性能优化

- 11.5.1 GC调优

    - 1.确定GC优化目标
    - 2.确定服务端内存占用
    - 3.GC数据的采集
    - 4.GC数据解读
    - 5.Java堆大小设置原则（如表11-1所示）
    - 6.垃圾收集器的选择
    - 7.一些GC调优误区

- 11.5.2 其他优化手段

### 11.6 总结

除了通过操作系统内核参数、Netty框架和JVM调优来提升单节点处理性能，还可以通过分布式集群的方式提升整个服务端的处理能力，把性能的压力分散到各个节点上。除了可以降低单个节点的风险，也可以利用云平台的弹性伸缩实现服务端的快速扩容，以应对突发的流量洪峰。如果每个节点负担过重，一旦某个节点宕机，流量会瞬间转移到其他节点，导致其他节点超负荷运行，系统的可靠性降低。通过“分布式+弹性伸缩”构建可平滑扩容的IoT服务端，是未来的一种主流模式。


## 第12章 静态检查修改不当引起性能下降案例

将业务代码提交到配置库做持续集成构建时，通常会对代码做静态检查，例如FindBugs、Checkstyle 等。很多项目对静态检查出的问题都有清零要求，除了编码和单元测试，修改静态检查问题也是开发人员的日常工作之一。

有些静态检查问题按照建议修改也许能够消除错误，但是放到整个业务上下文中就有问题，因此开发人员不能机械地以消除静态告警或错误为目标，而是要结合代码上下文和业务场景进行修改，防止因为修改不当引起性能问题，以及其他问题。


### 12.1 Edge Service性能严重下降问题

- 12.1.1 Edge Service热点代码分析
- 12.1.2 静态检查问题不是简单的一改了之
- 12.1.3 问题反思和改进

    - （1）静态检查本来是用于解决问题的，却因为修改不当引入了新的问题。对于工具和规则不要盲从，需要结合业务场景辩证地看待，而不是为了完成任务和指标而盲目修改。
    - （2）对于 Java 的克隆、对象拷贝等不太熟悉，也侧面反映出对 Java 基础知识掌握得不太扎实，需要加强基本功。
    - （3）静态检查问题的修改往往按照分类、分人的方式进行，例如针对某一类问题，都统一由某一个人修改。由于修改者对代码上下文并不理解，通常会按照工具提示和建议进行修改，这样就可能引入新的问题。
    - （4）对于关键路径代码的修改，除了做功能测试，还需要增加性能测试用例，例如每天CI/CD构建的时候跑一下基础性能用例，这样可以更早地发现性能问题。

### 12.2 克隆和浅拷贝

- 12.2.1 浅拷贝存在的问题
- 12.2.2 Netty的对象拷贝实现策略

### 12.3 总结

静态检查本来是为了提升代码质量，但是由于盲目按照工具的建议做修改，对业务运行态的关键代码路径及上下文场景都不清楚，最终导致了严重的性能问题。由于 Netty通常被用于高性能的通信框架，所以任何涉及性能的修改一定要谨慎，修改之后需要结合业务场景做相应的性能测试，以验证修改是否合理。


## 第13章 Netty性能统计误区案例

通常情况下，用户以黑盒的方式使用 Netty，通过 Netty完成协议消息的读取和发送，以及编解码操作，不需要关注Netty的底层实现细节。

在高并发场景下，往往需要统计系统的关键性能KPI数据，结合日志、告警等对故障进行定位分析，如果对 Netty的底层实现细节不了解，获取哪些关键性能数据及数据正确的获取方式都将成为难点。错误或者不准确的数据可能影响问题定位的思路和方向，导致问题迟迟得不到正确的解决。


### 13.1 时延毛刺排查相关问题

- 13.1.1 时延毛刺问题初步分析
- 13.1.2 服务调用链改进
- 13.1.3 都是同步思维惹的祸
- 13.1.4 正确的消息发送速度性能统计策略
- 13.1.5 常见的消息发送性能统计误区

### 13.2 Netty关键性能指标采集策略

- 13.2.1 Netty I/O线程池性能指标
- 13.2.2 Netty发送队列积压消息数
- 13.2.3 Netty消息读取速度性能统计

### 13.3 总结

当我们对服务调用时延进行精细化分析时，需要把 Netty通信框架底层的处理耗时数据也采集并进行分析，由于 Netty的 I/O 操作都是异步的，因此不能像传统同步调用那样做性能数据统计，需要注册性能统计监听器，在异步回调中完成计数。另外，Netty的I/O线程池、消息发送队列等实现比较特殊，与传统的Tomcat等框架实现策略不同，因此Netty的关键性能数据采集不能照搬JDK和Tomcat的做法。


## 第14章 gRPC的Netty HTTP/2实践案例

### 14.1 gRPC基础入门

- 14.1.1 RPC框架简介
- 14.1.2 当前主流的RPC框架
- 14.1.3 gRPC框架特点
- 14.1.4 为什么选择HTTP/2

### 14.2 gRPC Netty HTTP/2服务端工作机制

- 14.2.1 Netty HTTP/2服务端创建原理和源码分析
- 14.2.2 服务端接收HTTP/2请求消息原理和源码分析

    - 1.HTTP/2消息头的读取和处理
    - 2.HTTP/2消息体的处理

- 14.2.3 服务端发送HTTP/2 响应消息原理和源码分析

### 14.3 gRPC Netty HTTP/2客户端工作机制

- 14.3.1 Netty HTTP/2客户端创建原理和源码分析
- 14.3.2 客户端发送HTTP/2请求消息原理和源码分析
- 14.3.3 客户端接收HTTP/2响应消息原理和源码分析

### 14.4 gRPC消息序列化机制

- 14.4.1 Google Protobuf简介
- 14.4.2 消息的序列化原理和源码分析
- 14.4.3 消息的反序列化原理和源码分析

### 14.5 gRPC线程模型

- 14.5.1 服务端线程模型
- 14.5.2 客户端线程模型
- 14.5.3 线程模型总结

  消息的序列化和反序列化均由gRPC线程负责，而没有在Netty的Handler中做CodeC，这样可以降低Netty I/O线程的工作负载，提升系统的吞吐量。

  gRPC 采用的是网络 I/O 线程和业务调用线程分离的策略，在大部分场景下该策略是最优的。但是，对于那些接口逻辑非常简单，执行时间很短，不需要与外部网元交互、访问数据库或本地存储，也不需要等待其他同步操作的场景，则建议直接在 Netty I/O 线程中调用接口，不需要再发送到后端的业务线程池，避免了线程上下文切换，同时也消除了线程并发问题。

  当前Netty NIO线程和gRPC的SerializingExecutor之间没有映射关系，当线程数量比较多时，锁竞争会非常激烈，可以采用 I/O线程和 gRPC服务调用线程绑定的方式，降低出现锁竞争的概率，提升并发性能，通过线程绑定技术降低锁竞争的概率如图14-18所示。


### 14.6 总结

Netty 4.1 提供了完整的 HTTP/2 协议栈，用户可以基于 Netty 4.1 框架快速开发支持HTTP/2的服务端应用。通过学习Netty HTTP/2协议栈在gRPC中的应用，可以掌握HTTP/2客户端和服务端的创建、HTTP/2 消息的收发及序列化和反序列化等功能，学习和借鉴gRPC HTTP/2处理的优点，可以在实际项目中少走很多弯路。


## 第15章 Netty事件触发策略使用不当案例

针对Channel上发生的各种网络操作，例如链路创建、链路关闭、消息读写、链路注册和去注册等，Netty 将这些消息封装成事件，触发 ChannelPipeline 调用 ChannelHandler链，由系统或者用户实现的ChannelHandler对网络事件做处理。


### 15.1 channelReadComplete方法被调用多次问题

- 15.1.1 ChannelHandler调用问题
- 15.1.2 生产环境问题模拟重现

### 15.2 ChannelHandler使用的一些误区总结

- 15.2.1 channelReadComplete方法调用
- 15.2.2 ChannelHandler职责链调用

### 15.3 总结

在通常情况下，在做功能测试或者并发压力不大时，HTTP 请求消息可以一次性接收完成，此时ChannelHandler的 channelReadComplete方法会被调用一次，但是当一个整包消息经过多次读取才能完成解码时，channelReadComplete 方法就会被调用多次。如果业务的功能正确性依赖channelReadComplete 方法的调用次数，当客户端并发压力大或者采用 chunked 编码时，功能就会出错。因此，需要熟悉和掌握 Netty 的事件触发机制及ChannelHandler的调用策略，这样才能防止在生产环境中“踩坑”。


## 第16章 Netty流量整形应用案例

当系统负载压力比较大时，系统进入过负荷状态，可能是 CPU、内存资源已经过载，也可能是应用进程内部的资源几乎耗尽，如果继续全量处理业务，可能会导致长时间的Full GC、消息严重积压或者应用进程宕机，最终将压力转移到集群中的其他节点，引起级联故障。通过动态流控，拒绝一定比例新接入的请求消息，可以保障系统不被压垮。

除了动态流控，有时候还需要对消息的读取和发送速度做控制，以便消息能以较恒定的速度发送到下游网元，保护下游各系统不受突发的流量冲击，通过 Netty提供的流量整形功能，就可以达到控制消息读取和发送速度的目标。


### 16.1 Netty流量整形功能

- 16.1.1 通用的流量整形功能简介

  流量整形（Traffic Shaping）是一种主动调整流量输出速度的措施。一个典型的应用是基于下游网络节点的TPS指标控制本地流量的输出。流量整形与流量控制的主要区别在于，流量整形是对流量控制中需要丢弃的报文进行缓存—通常是将它们放入缓冲区或队列。当令牌桶有足够多的令牌时，再均匀地向外发送这些被缓存的报文。流量整形与流量控制的另一区别是，整形可能会增加延迟，而流控几乎不引入额外的延迟。


- 16.1.2 Netty流量整形功能简介

    - Netty内置了三种流量整形功能。

        - （1）单个链路的流量整形：ChannelTrafficShapingHandler，可以对某个链路的消息发送和读取速度进行控制。
        - （2）全局流量整形：GlobalTrafficShapingHandler，针对某个进程所有链路的消息发送和读取速度的总和进行控制。
        - （3）全局和单个链路综合型流量整形：GlobalChannelTrafficShapingHandler，同时对全局和单个链路的消息发送和读取速度进行控制。

    - Netty流量整形的主要作用有两个。

        - （1）防止由于上、下游网元性能不均衡导致下游网元被压垮，业务流程中断。
        - （2）防止由于通信模块接收消息过快，后端业务线程处理不及时，导致出现“撑死”问题。

### 16.2 Netty流量整形应用

- 16.2.1 流量整形示例代码
- 16.2.2 流量整形功能测试

### 16.3 Netty流量整形工作机制

- 16.3.1 流量整形工作原理和源码分析

  流量整形的工作原理：拦截channelRead和write方法，计算当前需要发送的消息大小，对读取和发送阈值进行判断，如果达到了阈值，则暂停读取和发送消息，待下一个周期继续处理，以实现在某个周期内对消息读写速度进行控制。


	- 1.消息读取的流量整形
	- 2.消息发送的流量整形

- 16.3.2 并发编程在流量整形中的应用

    - 1.volatile的使用
    - 2.减小锁的范围
    - 3.原子类

- 16.3.3 使用流量整形的一些注意事项总结

    - 1.流量整形ChannelHandler添加位置
    - 2.全局流量整形实例只需要创建一次
    - 3.流量整形参数调整不要过于频繁
    - 4.资源释放问题
    - 5.消息发送保护机制

### 16.4 总结

流量整形与流控的最大区别在于，流控会拒绝消息，流量整形不拒绝和丢弃消息，无论接收量多大，它总能以近似恒定的速度下发消息，跟变压器的原理和功能类似。


## 第17章 Netty SSL应用案例

作为一个高性能的NIO通信框架，基于Netty的行业应用非常广泛，不同的行业、不同的应用场景，面临的安全挑战也不同。例如基于Netty构建API Gateway，需要支持HTTPS，此时就要用到Netty的SSL相关功能。

Netty通过JDK的SSLEngine，以SslHandler的方式提供对SSL/TLS安全传输的支持，极大地简化了用户的开发工作，降低了开发难度。考虑到性能因素，除了支持原生的JDK SSLEngine，Netty还提供了对 OpenSSL的支持。尽管使用 Netty开发 SSL/TLS 程序相对比较简单，但是在实际应用中很容易犯错，本章对一些常见的错误用法进行归纳总结，避免大家犯同样的错。


### 17.1 Netty SSL功能简介

- 17.1.1 SSL安全特性

    - 1.单向认证
    - 2.双向认证
    - 3.CA认证

- 17.1.2 Netty SSL实现机制

### 17.2 Netty客户端SSL握手超时问题

- 17.2.1 握手超时原因定位
- 17.2.2 Netty SSL握手问题定位技巧

    - 常见的SSL握手失败原因

        - （1）证书不匹配，证书校验失败。
        - （2）证书过期。
        - （3）双方 SSL 引擎支持的 TSL/SSL 版本不一致，有些 SSL 引擎不支持高版本的TSL/SSL。
        - （4）域名校验失败。
        - （5）双方支持的加密算法不一致，导致算法套件匹配失败。

### 17.3 SSL握手性能问题

- 17.3.1 SSL握手性能热点分析
- 17.3.2 缓存和对象池

### 17.4 SSL事件监听机制

- 17.4.1 握手成功事件
- 17.4.2 SSL连接关闭事件

### 17.5 总结

当存在跨网络边界的 RPC调用时，往往需要通过 TLS/SSL对传输通道进行加密，以防止请求和响应消息中的敏感数据泄露。通过 Netty提供的 SslHandler可以方便地实现单向和双向认证，对于有较高性能诉求的场景，可以使用 OpenSSL引擎来提升 SSL/TLS的通信性能。


## 第18章 Netty HTTPS服务端高并发宕机案例

Netty提供了原生的 HTTPS 协议栈，用户可以基于该协议栈开发各种 HTTPS 应用，在互联网的高并发场景下，如果客户端并发量过大，而服务端又没有针对并发连接数进行流控，或者没有弹性伸缩能力，则很有可能宕机。

HTTPS服务端的性能优化，既有功能层面的小优化，又有架构层面的大优化，本章针对高并发场景下HTTPS服务端的可靠性保护和性能优化进行详细讲解。


### 18.1 Netty HTTPS服务端宕机问题

- 18.1.1 客户端大量超时
- 18.1.2 服务端内存泄漏原因分析
- 18.1.3 NioSocketChannel泄漏原因探究
- 18.1.4 高并发场景下缺失的可靠性保护

### 18.2 功能层面的可靠性优化

- 18.2.1 Netty HTTPS服务端可靠性优化

    - 1.HTTPS并发连接数流控
    - 2.切换JDK SSL引擎到OpenSSL
    - 3.HTTPS服务端弹性伸缩

- 18.2.2 HTTPS客户端优化

    - 1.HTTPS连接池调整
    - 2.超时时间调整

### 18.3 架构层面的可靠性优化

- 18.3.1 端到端架构问题剖析
- 18.3.2 HTTP Client切换到NIO
- 18.3.3 同步RPC调用切换到异步调用

    - 1.NIO与RPC调用方式的关系
    - 2.同步服务调用的缺点
    - 3.异步服务调用设计
    - 4.异步服务调用的优势

- 18.3.4 协议升级到HTTP/2

    - 1.HTT/1.1存在的问题
    - 2.HTTP/2 streaming调用

### 18.4 总结

随着信息安全和个人隐私数据保护的加强，越来越多的 RPC 框架开始支持 SSL/TLS传输通道加密，表面上增加数据加解密功能只对系统的性能有一些影响，但实际上系统的可靠性也会面临比较大的挑战，特别是高并发、低时延类的业务，一旦发生批量服务调用超时，就会导致大量的SSL链路重建，在业务高峰期，如果服务端可靠性设计欠缺，很有可能宕机，导致业务中断。

针对 HTTPS 服务的优化，除了功能上加强可靠性设计，更需要从架构层面做系统性的优化，借鉴gRPC的设计理念，基于HTTP/2构建异步流式 RPC调用是个不错的选择。


## 第19章 MQTT服务接入超时案例

Netty 4.1提供了MQTT协议栈，基于此可以非常方便地创建MQTT服务，尽管开发简单，但是在实际环境中会面临各种挑战，甚至会面临一些不遵循 MQTT 规范的端侧设备接入。

如果服务端没有考虑到各种异常场景，很难稳定运行，本章以生产环境 MQTT 服务无法提供接入服务为例，详细介绍MQTT服务和Netty在异常场景下的保护机制。


### 19.1 MQTT服务接入超时问题

- 19.1.1 生产环境问题现象
- 19.1.2 连接数膨胀原因分析
- 19.1.3 无效连接的关闭策略
- 19.1.4 问题总结

### 19.2 基于Netty的可靠性设计

- 19.2.1 业务定制I/O异常
- 19.2.2 链路的有效性检测
- 19.2.3 内存保护

### 19.3 总结

可靠性设计的关键在于对非预期异常场景的保护，应用层协议栈会考虑应用协议异常时通信双方应该怎么正确处理异常，但是对于那些不遵循协议规范实现的客户端，协议规范是无法强制约束对方的，特别是在物联网应用中，面对各种厂家的不同终端设备接入，服务端需要应对各种异常。只有可靠性做得足够好，MQTT服务才能更从容地应对海量设备的接入。


## 第20章 Netty实践总结

### 20.1 Netty学习策略

- 20.1.1 入门知识准备

    - 1.Java NIO类库
    - 2.Java多线程编程

- 20.1.2 Netty入门学习
- 20.1.3 项目实践
- 20.1.4 Netty源码阅读策略

### 20.2 Netty故障定位技巧

- 20.2.1 接收不到消息
- 20.2.2 内存泄漏
- 20.2.3 性能问题

### 20.3 总结

在实际项目中仅仅会用Netty是远远不够的，由于Netty承担了RPC调用工作，一旦发生问题会导致RPC或者微服务调用失败，由此引起的业务中断后果很严重。除了实践，需要熟练掌握Netty的核心类库和关键调度流程，这样才能得心应手地解决各种问题。


*XMind - Trial Version*