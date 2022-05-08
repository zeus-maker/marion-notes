# 【Marion-Micro】
微服务模版框架

## 一、目标

### 1. 作为传统单体服务改造成微服务架构的模板项目

### 2. 参考目前主流的模板项目开发

### 3. 技术栈全面可插拔式引入网关、RPC、服务注册发现、链路追踪、ELK、监控告警、定时任务等

### 4. 多模块开发，领域驱动设计、设计模式

## 二、技术选型

### 1. 开发语言：JAVA / JDK11

### 2. 数据库：MySQL | Redis | ElasticSearch

### 3. 开发框架：SpringBoot

### 4. 开发工具：Gradle/Maven私服

### 5. 项目部署：Docker/Jinkens/K8s

### 6. 开源组件：SpringCloud Gateway | OpenFeign | Nacos | Apollo | Sentinel | Sleuth + Zipkin | ELK | XXL-Job ｜ Kafka

### 7. 性能压测：Jemter | 全链路压测

## 三、项目结构

### 技术栈

- 1. maven
- 2. gitee

### 1. 目录设计

- 父模块定义

	- 1. 子模块定义
	- 2. 公用依赖和版本控制

		- 1. springboot版本

			- 2.2.2.RELEASE

		- 2. lombok版本

			- 1.8.22

		- 3. mysql驱动版本

			- 8.0.21

		- 4. JPA版本或者Mybatis-Plus

			- JPA-2.2.2.RELEASE

		- 5. redis版本

			- 2.2.2.RELEASE

		- 6. spring-cloud版本

			- Hoxton.SR1

		- 7. nacos注册中心版本

			- 2.2.1.RELEASE

		- 8. nacos配置中心版本

			- 2.2.1.RELEASE

	- 3. 微服务端口定义

		- 1. gatway：10000
		- 2. amin: 10010
		- 3. app: 10020
		- 4. web: 10030

- 1. 公用模块-common

	- 1. 目的

		- 管理每一个微服务公用的依赖、工具类、错误类、常量、验证错误，方便定位错误项目以及减少工具类的复制粘贴，微服务之间的RPC接口

	- 2. 依赖

		- 1. 父子模块依赖如何定义

		- 2. 父模块定义统一版本，子模块不需要声明版本
		- 3. 子模块定义<parent>
		- 4. 定义微服务公用的依赖

	- 2. 子目录

		- 1. RPC接口
		- 2. 工具类
		- 3. 错误类
		- 4. RPC接口类

	- 3. 优缺点

		- 1. 优点：统一管理
		- 2. 缺点：修改需要更新版本

- 2. 核心模块-core

	- 1. 目的

		- 1. 参考Spring-Core设计思路，IO操作、反射操作
		- 2. 只有部分微服务项目依赖

	- 2. 子目录

		- 核心工具类

			- 1. 分布式ID
			- 2. 加解密算法
			- 3. Jwt令牌

	- 3. 优缺点

		- 1. 优点：代码复用
		- 2. 缺点：新增需要发包，其他包可能不需要

- 3. 网关模块-gateway

	- 1. 目的

		- 1. 鉴权、限流、负载均衡、统一入口、服务注册发现
		- 2. 技术选型-SpringCloud Gateway

	- 2. 问题

		- 1. 启动依赖报错问题，解决不兼容问题

	- 3. 依赖说明

	          <spring-cloud.version>Hoxton.SR1</spring-cloud.version>
	          <spring-boot.version>2.2.2.RELEASE</spring-boot.version>
	  

	- 4. 集成nacos注册中心与配置中心

		- 1. 注册中心

			- 1. 添加依赖
			- 2. 在 application.properties 中配置 Nacos server 的地址

				- server.port=8070
				- spring.application.name=service-provider
				- spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848

			- 3. 通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能

		- 2. 配置中心

			- web和webflux不能一起使用
			- 1. 添加依赖
			- 2. 配置nacos sever地址
			- 3. 定义配置名称
			- 4. @RefreshScope实现配置自动更新
			- 5. 测试配置是否生效

				- 1. 定义controller
				- 2. curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example.properties&group=DEFAULT_GROUP&content=useLocalCache=true"

			- 6. 定义环境local/dev/prod

	- 5. 测试路由转发

		- 1. 转发报错：java.lang.NoClassDefFoundError: reactor/util/retry/Retry

			- Hoxton.SR1和2.2.2

	- 6. 定义路由转发规则

		- 1. /admin -> marion-micro-admin

- 4. 后台管理-admin

	- 1. 目的

		- 1. 前后端分离的后台管理项目Vue开发

	- 2. 依赖说明

	  <dependencies>
	      <dependency>
	          <groupId>org.springframework.boot</groupId>
	          <artifactId>spring-boot-starter</artifactId>
	      </dependency>
	      <dependency>
	          <groupId>org.springframework.boot</groupId>
	          <artifactId>spring-boot-starter-web</artifactId>
	      </dependency>
	      <dependency>
	          <groupId>mysql</groupId>
	          <artifactId>mysql-connector-java</artifactId>
	      </dependency>
	      <dependency>
	          <groupId>org.springframework.boot</groupId>
	          <artifactId>spring-boot-starter-data-jpa</artifactId>
	      </dependency>
	      <!--注册中心与配置中心 start-->
	      <dependency>
	          <groupId>com.alibaba.cloud</groupId>
	          <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
	      </dependency>
	      <dependency>
	          <groupId>com.alibaba.cloud</groupId>
	          <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
	      </dependency>
	      <!--注册中心与配置中心 end-->
	      <dependency>
	          <groupId>org.springframework.boot</groupId>
	          <artifactId>spring-boot-starter-test</artifactId>
	          <scope>test</scope>
	      </dependency>
	  </dependencies>
	  
	  

	- 3. 定义数据库连接和接口
	- 4. 引入common模块和core模块，取消多余模块

- 5. App接口-app

	- 1. 目的

		- 1. 提供给APP端接口
		- 2. 该模块可以拆分成多个微服务模块替换-api

	- 2. 依赖说明

- 6. Web接口-web

	- 1. 目的

		- 1. 提供给Web端接口

	- 2. 依赖说明

- 7. 公用模块

	- 1. 短信模块-sms
	- 2. 搜索模块-search
	- 3. 公用三方服务-third-party

### 2. 项目新建

- 1. 新建springboot多模块项目，定义父子模块

### 3. 依赖管理

- 1. 接入OpenFeign负载均衡

	- 1. web请求admin

		- rpc请求错误：Could not extract response
		- 1. 加入依赖
		- 2. @EnableFeignClients
		- 3. 返回String成功
		- 4. 请求测试

			- curl http://localhost:10030/rpc/user
			- curl http://localhost:10030/rpc/user/vo

		- 5. 测试服务注册发现

			- 1. 赋值一个admin启动10011
			- 2. 停止10011

	- 2. 接入降级

- 2. 接入Redis

	- 1. 测试Redis

		- curl http://localhost:10030/redis/set?key=user&val=1
		- curl http://localhost:10030/redis/get?key=user

- 3. 接入链路追踪

	- 1. 加入依赖
	- 2. 启动zipkin
	- 3. 配置yml
	- 4. 定义日志
	- 5. 持久化到MySQL

- 4. 接入Kafka
- 5. 接入ELK
- 6. 接入Prometheus+Grafana

## 四、代码设计

## 五、业务设计

## 项目部署

### 1. 测试多模块打包

- 1. 子模块不能加<relativePath/> <!-- lookup parent from repository -->
- 2. clean and package
- 3. 运行测试

### 2. docker部署

### 3. 代码发布

- 1. 发布到私服
- 2.发布到Gitee

### 4. Jinkens部署

*XMind - Trial Version*