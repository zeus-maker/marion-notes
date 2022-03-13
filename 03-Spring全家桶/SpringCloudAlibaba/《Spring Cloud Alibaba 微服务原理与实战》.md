# 《Spring Cloud Alibaba 微服务原理与实战》

## 第1章微服务的发展史

### 1.1 从单体架构到分布式架构的演进

- 1.1.1单体架构
- 1.1.2集群及垂直化
- 1.1.3 SOA
- 1.1.4微服务架构

### 1.2微服务架构带来的挑战

- 1.2.1微服务架构的优点
- 1.2.2微服务架构面临的挑战

### 1.3如何实现微服务架构

- 1.3.1微服务架构图
- 1.3.2微服务架构下的技术挑战

## 第2章微服务解决方案之Spring Cloud

### 2.1什么是Spring Cloud

### 2.2 Spring Cloud版本简介

### 2.3 Spring Cloud规范下的实现

### 2.4 Spring Cloud Netflix

### 2.5 Spring Cloud Alibaba

- 2.5.1 Spring Cloud Alibaba的优势
- 2.5.2 Spring Cloud Alibaba的版本

## 第3章Spring Cloud的核心之Spring Boot

### 3.1重新认识Spring Boot

- 3.1.1 Spring loC/DI
- 3.1.2 Bean装配方式的升级
- 3.1.3 Spring Boot的价值

### 3.2快速构建Spring Boot应用

### 3.3 Spring Boot自动装配的原理

- 3.3.1自动装配的实现
- 3.3.2 EnableAutoConfiguration
- 3.3.3 AutoConfigurationlmportSelector
- 3.3.4自动装配原理分析
- 3.3. 5 @ Conditional条件装配
- 3.3.6 spring-autoconfigure- metadata

### 3.4手写实现一个Starter

- 3.4.1 Starter的命名规范
- 3.4.2实现基于Redis的Starter

### 3.5本章小结

## 第4章微服务架构下的服务治理

### 4.1如何理解Apache Dubbo

### 4.2 Apache Dubbo实现远程通信

### 4.3 Spring Boot集成Apache Dubbo

### 4.4快速上手ZooKeeper

- 4.4.1 ZooKeeper的安装
- 4.4.2 ZooKeeper的数据结构
- 4.4.3 ZooKeeper的特性
- 4.4.4 Watcher机制
- 4.4.5常见应用场景分析

### 4.5 Apache Dubbo集成ZooKeeper实现服务注册

- 4.5.1 Apache Dubbo集成ZooKeeper实现服务注册的步骤
- 4.5.2 ZooKeeper注册中心的实现原理，

### 4.6实战Dubbo Spring Cloud

- 4.6.1实现Dubbo服务提供方
- 4.6.2实现Dubbo服务调用方

### 4.7 Apache Dubbo的高级应用

- 4.7.1集群容错
- 4.7.2负载均衡
- 4.7.3服务降级
- 4.7.4主机绑定规则

### 4.8 Apache Dubbo核心源码分析

- 4.8.1源码构建
- 4.8.2 Dubbo的核心之SPI
- 4.8.3无处不在的自适应扩展点
- 4.8.4 Dubbo中的IoC和AOP
- 4.8.5 Dubbo和Spring完美集成的原理

### 4.9本章小结

## 第5章服务注册与发现

### 5.1什么是Alibaba Nacos

### 5.2 Nacos的基本使用

- 5.2.1 Nacos的安装
- 5.2.2 Nacos服务注册发现相关API说明
- 5.2.3 Nacos 集成Spring Boot实现服务注册与发现

### 5.3 Nacos的高可用部署

- 5.3.1安装环境要求
- 5.3.2安装包及环境准备
- 5.3.3集群配置
- 5.3.4配置MySQL数据库
- 5.3.5启动Nacos服务

### 5.4 Dubbo使用Nacos实现注册中心

### 5.5 Spring Cloud Alibaba Nacos Discovery

- 5.5.1服务端开发
- 5.5.2消费端开发
- 5.6 Nacos实现原理分析

### 5.7深入解读Nacos源码

- 5.7.1 Spring Cloud什么时候完成服务注册
- 5.7.2 NacosServiceRegistry的实现
- 5.7.3从源码层面分析Nacos服务注册的原理
- 5.7. 4揭秘服务提供者地址查询
- 5.7.5分析Nacos服务地址动态感知原理

### 5.8本章小结

## 第6章Nacos实现统一配置管理

### 6.1 Nacos配置中心简介

### 6.2 Nacos集成Spring Boot实现统一 配置管理

- 6.2.1项目准备
- 6.2.2启动Nacos Server
- 6.2.3创建配置
- 6.2.4启动服务并测试

### 6.3 Spring Cloud Alibaba Nacos Config

- 6.3.1 Nacos Config的基本应用
- 6.3.2动态更新配置
- 6.3.3基于Data ID配置YAML的文件扩展名
- 6.3.4不同环境的配置切换
- 6.3.5 Nacos Config自定义Namespace和Group

### 6.4 Nacos Config实现原理解析

- 6.4.1配置的CRUD
- 6.4.2动态监听之Pull Or Push

### 6.5 Spring Cloud如何实现配置的加载

- 6.5.1 PropertySourceBootstrapConfiguration
- 6.5.2 PropertySourceLocator

### 6.6 Nacos Config核心源码解析

- 6.6.1 NacosFactory.createConfigService
- 6.6.2 NacosConfigService构造
- 6.6.3 ClientWorker
- 6.6.4 ClientWorker.checkConfigInfo
- 6.6.5 LongPollingRunnable.run
- 6.6.6服务端长轮询处理机制
- 6.6.7 ClientL ongPolling

### 6.7本章小结

## 第7章基于Sentinel的微服务限流及熔断

### 7.1服务限流的作用及实现

- 7.1.1计数器算法
- 7.1.2滑动窗口算法
- 7.1.3令牌桶限流算法
- 7.1.4漏桶限流算法

### 7.2服务熔断与降级

### 7.3分布式限流框架Sentinel

- 7.3.1 Sentinel的特性
- 7.3.2 Sentinel的组成
- 7.3.3 Sentinel Dashboard的部署

### 7.4 Sentinel的基本应用

- 7.4.1 Sentinel实现限流
- 7.4.2资源的定义方式
- 7.4.3 Sentinel资源保护规则
- 7.4.4 Sentinel实现服务熔断

### 7.5 Spring Cloud集成SentineI实践

- 7.5.1 Sentinel接入Spring Cloud
- 7.5.2基于Sentinel Dashboard来实现流控配置
- 7.5.3 自定义URL限流异常
- 7.5.4 URL资源清洗

### 7.6 Sentinel集成Nacos实现动态流控规则

### 7.7 Sentinel Dashboard集成Nacos实现规则同步

- 7.7.1 Sentinel Das hboard源码修改
- 7.7.2 Sentinel Das hboard规则数据同步

### 7.8 Dubbo集成Sentinel实现限流

- 7.8.1 Dubbo服务接入Sentinel Dashboard
- 7.8.2 Dubbo服务限流规则配置

### 7.9 Sentinel热点限流

- 7.9.1热点参数限流的使用
- 7.9.2 @ SentinelResource热点参数限流
- 7.9.3热点参数规则说明

### 7.10 Sentinel的工作原理

### 7.11 Spring Cloud Sentinel工作原理分析

### 7. 12 Sentinel核心源码分析

- 7.12.1限流的源码实现
- 7.12.2实时指标数据统计
- 7.12.3服务降级的实现原理
- 7.13本章小结

## 第8章分布式事务

### 8.1分布式事务问题的理论模型

- 8.1.1 X/Open分布式事务模型
- 8.1.2两阶段提交协议
- 8.1.3三阶段提交协议
- 8.1.4 CAP定理和BASE理论
- 8.2.1 TCC补偿型方案

### 8.2分布式事务问题的常见解决方案

- 8.2.2基于可靠性消息的最终一致性方案
- 8.2.3最大努力通知型

### 8.3分布式事务框架Seata

- 8.3.1 AT模式
- 8.3.2 Saga模式

### 8.4 Seata的安装

- 8.4.1 file存储模式
- 8.4.2 db存储模式
- 8.4.3 Seata服务端配置中心说明

### 8.5 AT模式Dubbo集成Seata

- 8.5.1项目准备
- 8.5.2数据库准备
- 8.5.3核心方法说明
- 8.5.4项目启动顺序及访问
- 8.5.5整合Seata实现分布式事务

### 8.6 Spring Cloud Alibaba Seata

- 8.6.1 Spring Cloud项目准备
- 8.6.2集成Spring Cloud Alibaba Seata
- 8.6.3关于事务分组的说明

### 8.7 Seata AT模式的实现原理

- 8.7.1 AT模式第一阶段的实现原理
- 8.7.2 AT模式第二阶段的原理分析
- 8.7.3关于事务的隔离性保证

### 8.8 本章小结

## 第9章RocketMQ分布式消息通信

### 9.1 什么是RocketMQ

- 9.1.1 RocketMQ的应用场景

	- ●削峰填谷:诸如秒杀抢红包、企业开门红等大型活动皆会带来较高的流量脉冲,很可能因没做相应的保护而导致系统超负荷甚至崩溃,或因限制太过导致请求大量失败而影响用户体验, RocketMQ可提供削峰填谷的服务来解决这些问题。
	- ●异步解耦:交易系统作为淘宝/天猫主站最核心的系统,每笔交易订单数据的产生会引起几百个下游业务系统的关注,包括物流、购物车、积分、流计算分析等,整体业务系统庞大而且复杂, RocketMQ可实现异步通信和应用解耦,确保主站业务的连续性。
	- ●顺序收发:细数--下，日常需要保证顺序的应用场景非常多,例如证券交易过程中的时间优先原则,交易系统中的订单创建、支付、退款等流程,航班中的旅客登机消息处理等。与先进先出( First In First Out ,缩写FIFO )原理类似，RocketMQ提供的顺序消息即保证消息的FIFO。
	- ●分布式事务一 致性:交易系统、红包等场景需要确保数据的最终一 致性,大量引入RocketMQ的分布式事务,既可以实现系统之间的解耦,又可以保证最终的数据一致性。
	- ●大数据分析:数据在“流动”中产生价值，传统数据分析大都基于批量计算模型,无法做到实时的数据分析,利用RocketMQ与流式计算引擎相结合,可以很方便地实现对业务数据进行实时分析。
	- ●分布式缓存同步:天猫双11大促,各个分会场琳琅满目的商品需要实时感知价格的变化,大量并发访问会导致会场页面响应时间长,集中式缓存因为带宽瓶颈限制商品变更的访问流量,通过RocketMQ构建分布式缓存,可实时通知商品数据的变化。

- 9.1.2 RocketMQ的安装
- 9.1.3 RocketMQ如何发送消息
- 9.1.4 RocketMQ如何消费消息

### 9.2 Spring Cloud Alibaba RocketMQ

- 9.2.1 Spring Cloud Alibaba RocketMQ架构图
- 9.2.2 Spring Cloud Stream消息发送流程
- 9.2.3 RocketMQ Binder集成消息发送
- 9.2.4 RocketMQ Binder集成消息订阅
- 9.2.5 Spring Cloud Stream消息订阅流程

### 9.3 RocketMQ集群管理

- 9.3.1整体架构设计
- 9.3.2基本概念
- 9.3.3为什么放弃ZooKeeper而选择NameServer

### 9.4 如何实现顺序消息

- 9.4.1顺序消息的使用场景
- 9.4.2如何发送和消费顺序消息
- 9.4.3顺序发送的技术原理
- 9.4.4普通发送的技术原理
- 9.4.5顺序消费的技术原理
- 9.4.6并发消费的技术原理
- 9.4.7消息的幂等性

### 9.5如何实现事务消息

- 9.5.1事务消息的使用场景
- 9.5.2如何发送事务消息
- 9.5.3事务消息的技术原理
- 9.6.1顺序写盘

### 9.6高性能设计

- 9.6.2消费队列设计
- 9.6.3消息跳跃读取
- 9.6.4数据零拷贝
- 9.6.5动态伸缩能力
- 9.6.6消息实时投递

### 9.7高可用设计

- 9.7.1消息发送重试机制
- 9.7.2故障规避机制
- 9.7.3同步刷盘与异步刷盘
- 9.7.4主从复制
- 9.7.5 读写分离
- 9.7.6消费重试机制
- 9.7.7 ACK机制
- 9.7.8 Broker集群部署

### 9.8本章小结

## 第10章微服务网关之Spring Cloud Gateway

### 10.1 API网关的作用

- 10.1.1统一认证鉴权
- 10.1.2灰度发布

### 10.2 网关的本质及技术选型

- 10.2.1 OpenResty
- 10.2.2 Spring Cloud Zuul 
- 10.2.3 Spring Cloud Gateway

### 10.3 Spring Cloud Gateway网关实战

- 10.3.1 spring-cloud-gateway-service
- 10.3.2 spring-cloud-gateway-sample

### 10.4 Spring Cloud Gateway原理分析

### 10.5 Route Predicate Factories

- 10.5.1指定时间规则匹配路由
- 10.5.2 Cookie匹配路由
- 10.5.3 Header匹配路由
- 10.5.4 Host匹配路由
- 10.5.5请求方法匹配路由
- 10.5.6请求路径匹配路由

### 10.6 Gateway Filter Factories

- 10.6.1 GatewayFilter
- 10.6.2 GlobalFilter

### 10.7 自定义过滤器

- 10.7.1 自定义GatewayFilter
- 10.7.2 自定义GlobalFilter

### 10.8 Spring Cloud Gateway集成Nacos实现请求负载

### 10.9 Spring Cloud Gateway集成Sentine|网关限流

- 10.9.1 Route维度限流
- 10.9.2自定义API分组限流
- 10.9.3自定义异常
- 10.9.4网关流控控制台
- 10.9.5网关限流原理

### 10.10本章小结

*XMind - Trial Version*