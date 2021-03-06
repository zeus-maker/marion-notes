# 《Go语言精进之路：从新手到高手的编程思想、方法和技巧》

## 前言

## 第一部分 熟知Go语言的一切

### 第1条 了解Go语言的诞生与演进

- 1.1 Go语言的诞生
- 1.2 Go语言的早期团队和演进历程
- 1.3 Go语言正式发布并开源
- 2.1 Go语言的先祖

### 第2条 选择适当的Go语言版本

- 2.2 Go语言的版本发布历史
- 2.3 Go语言的版本选择建议

### 第3条 理解Go语言的设计哲学

- 3.1 追求简单，少即是多
- 3.2 偏好组合，正交解耦
- 3.3 原生并发，轻量高效
- 3.4 面向工程，“自带电池”

### 第4条 使用Go语言原生编程思维来写Go代码

- 4.1 语言与思维——来自大师的观点
- 4.2 现实中的“投影”
- 4.3 Go语言原生编程思维

## 第二部分 项目结构、代码风格与标识符命名

### 第5条 使用得到公认且广泛使用的项目结构

- 5.1 Go项目的项目结构
- 5.2 Go语言典型项目结构

### 第6条 提交前使用gofmt格式化源码

- 6.1 gofmt：Go语言在解决规模化问题上的最佳实践
- 6.2 使用gofmt
- 6.3 使用goimports
- 6.4 将gofmt/goimports与IDE或编辑器工具集成

### 第7条 使用Go命名惯例对标识符进行命名

- 7.1 简单且一致
- 7.2 利用上下文环境，让最短的名字携带足够多的信息

## 第三部分 声明、类型、语句与控制结构

### 第8条 使用一致的变量声明形式

- 8.1 包级变量的声明形式
- 8.2 局部变量的声明形式

### 第9条 使用无类型常量简化代码

- 9.1 Go常量溯源
- 9.2 有类型常量带来的烦恼
- 9.3 无类型常量消除烦恼，简化代码

### 第10条 使用iota实现枚举常量

### 第11条 尽量定义零值可用的类型

- 11.1 Go类型的零值
- 11.2 零值可用

### 第12条 使用复合字面值作为初值构造器

- 12.1 结构体复合字面值
- 12.2 数组/切片复合字面值
- 12.3 map复合字面值

### 第13条 了解切片实现原理并高效使用

- 13.1 切片究竟是什么
- 13.2 切片的高级特性：动态扩容
- 13.3 尽量使用cap参数创建切片

### 第14条 了解map实现原理并高效使用

- 14.1 什么是map
- 14.2 map的基本操作
- 14.3 map的内部实现
- 14.4 尽量使用cap参数创建map

### 第15条 了解string实现原理并高效使用

- 15.1 Go语言的字符串类型
- 15.2 字符串的内部表示
- 15.3 字符串的高效构造
- 15.4 字符串相关的高效转换

### 第16条 理解Go语言的包导入

- 16.1 Go程序构建过程
- 16.2 究竟是路径名还是包名
- 16.3 包名冲突问题

### 第17条 理解Go语言表达式的求值顺序

- 17.1 包级别变量声明语句中的表达式求值顺序
- 17.2 普通求值顺序
- 17.3 赋值语句的求值
- 17.4 switch/select语句中的表达式求值

### 第18条 理解Go语言代码块与作用域

- 18.1 Go代码块与作用域简介
- 18.2 if条件控制语句的代码块
- 18.3 其他控制语句的代码块规则简介

### 第19条 了解Go语言控制语句惯用法及使用注意事项

- 19.1 使用if控制语句时应遵循“快乐路径”原则
- 19.2 for range的避“坑”指南
- 19.3 break跳到哪里去了
- 19.4 尽量用case表达式列表替代fallthrough

## 第四部分 函数与方法

### 第20条 在init函数中检查包级变量的初始状态

- 20.1 认识init函数
- 20.2 程序初始化顺序
- 20.3 使用init函数检查包级变量的初始状态

### 第21条 让自己习惯于函数是“一等公民”

- 21.1 什么是“一等公民”
- 21.2 函数作为“一等公民”的特殊运用

### 第22条 使用defer让函数更简洁、更健壮

- 22.1 defer的运作机制
- 22.2 defer的常见用法
- 22.3 关于defer的几个关键问题

### 第23条 理解方法的本质以选择正确的receiver类型

- 23.1 方法的本质
- 23.2 选择正确的receiver类型
- 23.3 基于对Go方法本质的理解巧解难题

### 第24条 方法集合决定接口实现

- 24.1 方法集合
- 24.2 类型嵌入与方法集合
- 24.3 defined类型的方法集合
- 24.4 类型别名的方法集合

### 第25条 了解变长参数函数的妙用

- 25.1 什么是变长参数函数
- 25.2 模拟函数重载
- 25.3 模拟实现函数的可选参数与默认参数
- 25.4 实现功能选项模式

## 第五部分 接口

### 第26条 了解接口类型变量的内部表示

- 26.1 nil error值!= nil
- 26.2 接口类型变量的内部表示
- 26.3 输出接口类型变量内部表示的详细信息
- 26.4 接口类型的装箱原理

### 第27条 尽量定义小接口

- 27.1 Go推荐定义小接口
- 27.2 小接口的优势
- 27.3 定义小接口可以遵循的一些点

### 第28条 尽量避免使用空接口作为函数参数类型

### 第29条 使用接口作为程序水平组合的连接点

- 29.1 一切皆组合
- 29.2 垂直组合回顾
- 29.3 以接口为连接点的水平组合

### 第30条 使用接口提高代码的可测试性

- 30.1 实现一个附加免责声明的电子邮件发送函数
- 30.2 使用接口来降低耦合

## 第六部分 并发编程

### 第31条 优先考虑并发设计

- 31.1 并发与并行
- 31.2 Go并发设计实例

### 第32条 了解goroutine的调度原理

- 32.1 goroutine调度器
- 32.2 goroutine调度模型与演进过程
- 32.3 对goroutine调度器原理的进一步理解
- 32.4 调度器状态的查看方法
- 32.5 goroutine调度实例简要分析

### 第33条 掌握Go并发模型和常见并发模式

- 33.1 Go并发模型
- 33.2 Go常见的并发模式

### 第34条 了解channel的妙用

- 34.1 无缓冲channel
- 34.2 带缓冲channel
- 34.3 nil channel的妙用
- 34.4 与select结合使用的一些惯用法

### 第35条 了解sync包的正确用法

- 35.1 sync包还是channel
- 35.2 使用sync包的注意事项
- 35.3 互斥锁还是读写锁
- 35.4 条件变量
- 35.5 使用sync.Once实现单例模式
- 35.6 使用sync.Pool减轻垃圾回收压力

### 第36条 使用atomic包实现伸缩性更好的并发读取

- 36.1 atomic包与原子操作
- 36.2 对共享整型变量的无锁读写
- 36.3 对共享自定义类型变量的无锁读写

## 第七部分 错误处理

### 第37条 了解错误处理的4种策略

- 37.1 构造错误值
- 37.2 透明错误处理策略
- 37.3 “哨兵”错误处理策略
- 37.4 错误值类型检视策略
- 37.5 错误行为特征检视策略

### 第38条 尽量优化反复出现的if err != nil

- 38.1 两种观点
- 38.2 尽量优化
- 38.3 优化思路

### 第39条 不要使用panic进行正常的错误处理

- 39.1 Go的panic不是Java的checked exception
- 39.2 panic的典型应用
- 39.3 理解panic的输出信息

## 第八部分 测试、性能剖析与调试

### 第40条 理解包内测试与包外测试的差别

- 40.1 官方文档的“自相矛盾”
- 40.2 包内测试与包外测试

### 第41条 有层次地组织测试代码

- 41.1 经典模式——平铺
- 41.2 xUnit家族模式
- 41.3 测试固件

### 第42条 优先编写表驱动的测试

- 42.1 Go测试代码的一般逻辑
- 42.2 表驱动的测试实践
- 42.3 表驱动测试的优点
- 42.4 表驱动测试实践中的注意事项

### 第43条 使用testdata管理测试依赖的外部数据文件

- 43.1 testdata目录
- 43.2 golden文件惯用法

### 第44条 正确运用fake、stub和mock等辅助单元测试

- 44.1 fake：真实组件或服务的简化实现版替身
- 44.2 stub：对返回结果有一定预设控制能力的替身
- 44.3 mock：专用于行为观察和验证的替身

### 第45条 使用模糊测试让潜在bug无处遁形

- 45.1 模糊测试在挖掘Go代码的潜在bug中的作用
- 45.2 go-fuzz的初步工作原理
- 45.3 go-fuzz使用方法
- 45.4 使用go-fuzz建立模糊测试的示例
- 45.5 让模糊测试成为“一等公民”

### 第46条 为被测对象建立性能基准

- 46.1 性能基准测试在Go语言中是“一等公民”
- 46.2 顺序执行和并行执行的性能基准测试
- 46.3 使用性能基准比较工具
- 46.4 排除额外干扰，让基准测试更精确

### 第47条 使用pprof对程序进行性能剖析

- 47.1 pprof的工作原理
- 47.2 使用pprof进行性能剖析的实例

### 第48条 使用expvar输出度量数据，辅助定位性能瓶颈点

- 48.1 expvar包的工作原理
- 48.2 自定义应用通过expvar输出的度量数据
- 48.3 输出数据的展示

### 第49条 使用Delve调试Go代码

- 49.1 关于调试，你首先应该知道的几件事
- 49.2 Go调试工具的选择
- 49.3 Delve调试基础、原理与架构
- 49.4 并发、Coredump文件与挂接进程调试

## 第九部分 标准库、反射与cgo

### 第50条 理解Go TCP Socket网络编程模型

- 50.1 TCP Socket网络编程模型
- 50.2 TCP连接的建立
- 50.3 Socket读写
- 50.4 Socket属性
- 50.5 关闭连接

### 第51条 使用net/http包实现安全通信

- 51.1 HTTPS：在安全传输层上运行的HTTP协议
- 51.2 HTTPS安全传输层的工作机制
- 51.3 非对称加密和公钥证书
- 51.4 对服务端公钥证书的校验
- 51.5 对客户端公钥证书的校验

### 第52条 掌握字符集的原理和字符编码方案间的转换

- 52.1 字符与字符集
- 52.2 Unicode字符集的诞生与UTF-8编码方案
- 52.3 字符编码方案间的转换

### 第53条 掌握使用time包的正确方式

- 53.1 时间的基础操作
- 53.2 时间的格式化输出
- 53.3 定时器的使用

### 第54条 不要忽略对系统信号的处理

- 54.1 为什么不能忽略对系统信号的处理
- 54.2 Go语言对系统信号处理的支持
- 54.3 使用系统信号实现程序的优雅退出

### 第55条 使用crypto下的密码学包构建安全应用

- 55.1 Go密码学包概览与设计原则
- 55.2 分组密码算法
- 55.3 公钥密码
- 55.4 单向散列函数
- 55.5 消息认证码
- 55.6 数字签名
- 55.7 随机数生成

### 第56条 掌握bytes包和strings包的基本操作

- 56.1 查找与替换
- 56.2 比较
- 56.3 分割
- 56.4 拼接
- 56.5 修剪与变换
- 56.6 快速对接I/O模型

### 第57条 理解标准库的读写模型

- 57.1 直接读写字节序列
- 57.2 直接读写抽象数据类型实例
- 57.3 通过包裹类型读写数据

### 第58条 掌握unsafe包的安全使用模式

- 58.1 简洁的unsafe包
- 58.2 unsafe包的典型应用
- 58.3 正确理解unsafe.Pointer与uintptr
- 58.4 unsafe.Pointer的安全使用模式

### 第59条 谨慎使用reflect包提供的反射能力

- 59.1 Go反射的三大法则
- 59.2 反射世界的入口
- 59.3 反射世界的出口
- 59.4 输出参数、interface{}类型变量及反射对象的可设置性

### 第60条 了解cgo的原理和使用开销

- 60.1 Go调用C代码的原理
- 60.2 在Go中使用C语言的类型
- 60.3 在Go中链接外部C库
- 60.4 在C中使用Go函数
- 60.5 使用cgo的开销
- 60.6 使用cgo代码的静态构建

## 第十部分 工具链与工程实践

### 第61条 使用module管理包依赖

- 61.1 Go语言包管理演进回顾
- 61.2 Go module：Go包依赖管理的生产标准
- 61.3 Go module代理
- 61.4 升级module的主版本号

### 第62条 构建最小Go程序容器镜像

- 62.1 镜像：继承中的创新
- 62.2 镜像是个筐：初学者的认知
- 62.3 理性回归：builder模式的崛起
- 62.4 “像赛车那样减重”：追求最小镜像
- 62.5 “要有光”：对多阶段构建的支持

### 第63条 自定义Go包的导入路径

- 63.1 govanityurls
- 63.2 使用govanityurls

### 第64条 熟练掌握Go常用工具

- 64.1 获取与安装
- 64.2 包或module检视
- 64.3 构建
- 64.4 运行与诊断
- 64.5 格式化与静态代码检查
- 64.6 重构
- 64.7 查看文档
- 64.8 代码导航与洞察

### 第65条 使用go generate驱动代码生成

- 65.1 go generate：Go原生的代码生成“驱动器”
- 65.2 go generate的工作原理
- 65.3 go generate的应用场景

### 第66条 牢记Go的常见“陷阱”

- 66.1 语法规范类
- 66.2 标准库类