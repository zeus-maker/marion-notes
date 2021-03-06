# 《Elasticsearch搜索引擎构建入门与实战》

## 第1篇 Elasticsearch基础
（阅读时间：4小时）
2022年02月26号

### 第1章 Elasticsearch入门
（阅读时间：1小时）

- 1.1 Elasticsearch概述

	- 1.1.1 Elasticsearch简介
	- 1.1.2 Elasticsearch的基本概念
	- 1.1.3 Elasticsearch和关系型数据库的对比

- 1.2 Elasticsearch的架构原理

	- 1.2.1 节点职责
	- 1.2.2 主分片和副分片
	- 1.2.3 路由计算
	- 1.2.4 文档读写过程

- 1.3 Elasticsearch的应用场景

	- 1.3.1 搜索引擎
	- 1.3.2 推荐系统
	- 1.3.3 二级索引
	- 1.3.4 日志分析

- 1.4 Elasticsearch的安装

	- 1.4.1 单机模式安装
	- 1.4.2 集群模式安装

- 1.5 Elasticsearch搜索入门

	- 1.5.1 创建索引
	- 1.5.2 写入文档
	- 1.5.3 根据_id搜索文档
	- 1.5.4 根据一般字段搜索文档
	- 1.5.5 根据文本字段搜索文档

### 第2章 Elasticsearch客户端实战

- 2.1 Kibana客户端简介

	- 2.1.1 Kibana的安装
	- 2.1.2 在Kibana中搜索文档

- 2.2 Java客户端简介

	- 2.2.1 Java客户端的使用
	- 2.2.2 Java带验证客户端的使用
	- 2.2.3 Java客户端搜索文档

- 2.3 Spring Boot客户端简介

	- 2.3.1 创建Spring Boot客户端

	- 2.3.2 Spring Boot客户端搜索文档

- 2.4 Jest客户端简介

	- 2.4.1 创建Jest客户端
	- 2.4.2 Jest客户端搜索文档

### 第3章 Elasticsearch基础操作

本章主要介绍ES的基础操作，具体包括索引、映射和文档的相关操作。其中，在文档操作中将分别介绍单条操作和批量操作。在生产实践中经常会通过程序对文档进行操作，因此在介绍文档操作时会分别介绍DSL请求形式和Java的高级REST编码形式。


- 3.1 索引操作

	- 3.1.1 创建索引
	- 3.1.2 删除索引
	- 3.1.3 关闭索引
	- 3.1.4 打开索引
	- 3.1.5 索引别名

		- 1. 假设当前日期是4月1日，需要搜索过去的3个月的日志索引，如果分别去3个索引中进行搜索，这种编码方案比较低效。此时可以创建一个别名last_three_month，设置前面的3个索引的别名为last_three_month，然后在last_three_month中进行搜索即可
		- 2. 当一个别名只指向一个索引时，写入数据的请求可以指向这个别名，如果这个别名指向多个索引（就像上面的例子），则写入数据的请求是不可以指向这个别名的

			- 将目标索引的is_write_index属性值设置为true来指定该索引可用于执行数据写入操作

		- 3. 引入别名之后，还可以用别名表示索引之间的替代关系

- 3.2 映射操作

	- 3.2.1 查看映射
	- 3.2.2 扩展映射
	- 3.2.3 基本的数据类型

		- 1.keyword类型

		  keyword类型是不进行切分的字符串类型。这里的“不进行切分”指的是：在索引时，对keyword类型的数据不进行切分，直接构建倒排索引；在搜索时，对该类型的查询字符串不进行切分后的部分匹配。keyword类型数据一般用于对文档的过滤、排序和聚合。
		  
		  在现实场景中，keyword经常用于描述姓名、产品类型、用户ID、URL和状态码等。keyword类型数据一般用于比较字符串是否相等，不对数据进行部分匹配，因此一般查询这种类型的数据时使用term查询。
		  

		- 2.text类型

		  text类型是可进行切分的字符串类型。这里的“可切分”指的是：在索引时，可按照相应的切词算法对文本内容进行切分，然后构建倒排索引；在搜索时，对该类型的查询字符串按照用户的切词算法进行切分，然后对切分后的部分匹配打分。
		  
		  term搜索用于搜索值和文档对应的字段是否完全相等，而对于text类型的数据，在建立索引时ES已经进行了切分并建立了倒排索引，因此使用term没有搜索到数据。一般情况下，搜索text类型的数据时应使用match搜索
		  

		- 3.数值类型

		  ES支持的数值类型有long、integer、short、byte、double、foat、half_foat、scaled_foat和unsigned_long等。各类型所表达的数值范围可以参考官方文档，网址为https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html。为节约存储空间并提升搜索和索引的效率，在实际应用中，在满足需求的情况下应尽可能选择范围小的数据类型。比如，年龄字段的取值最大值不会超过200，因此选择byte类型即可。数值类型的数据也可用于对文档进行过滤、排序和聚合。
		  

		- 4.布尔类型

		  布尔类型使用boolean定义，用于业务中的二值表示，如商品是否售罄，房屋是否已租，酒店房间是否满房等。写入或者查询该类型的数据时，其值可以使用true和false，或者使用字符串形式的"true"和"false"
		  

		- 5.日期类型

		  在ES中，日期类型的名称为date。ES中存储的日期是标准的UTC格式。
		  
		  日期类型的默认格式为strict_date_optional_time||epoch_millis。其中，strict_date_optional_time的含义是严格的时间类型，支持yyyy-MM-dd、yyyyMMdd、yyyyMMddHHmmss、yyyy-MM-ddTHH:mm:ss、yyyy-MM-ddTHH:mm:ss.SSS和yyyy-MM-ddTHH:mm:ss.SSSZ等格式，epoch_millis的含义是从1970年1月1日0点到现在的毫秒数。
		  
		  日期类型默认不支持yyyy-MM-dd HH:mm:ss格式
		  

	- 3.2.4 复杂的数据类型

		- 1.数组类型

		  ES数组没有定义方式，其使用方式是开箱即用的，即无须事先声明，在写入时把数据用中括号[]括起来，由ES对该字段完成定义。
		  
		  数组类型的字段适用于元素类型的搜索方式，也就是说，数组元素适用于什么搜索，数组字段就适用于什么搜索
		  

		- 2.对象类型

		  1. 在实际业务中，一个文档需要包含其他内部对象。例如，在酒店搜索需求中，用户希望酒店信息中包含评论数据。评论数据分为好评数量和差评数量。为了支持这种业务，在ES中可以使用对象类型。和数组类型一样，对象类型也不用事先定义，在写入文档的时候ES会自动识别并转换为对象类型。
		  
		  2. 根据对象类型中的属性进行搜索，可以直接用“。”操作符进行指向
		  
		  3. 对象内部还可以包含对象
		  

		- 3.地理类型

		  在移动互联网时代，用户借助移动设备产生的消费也越来越多。例如，用户需要根据某个地理位置来搜索酒店，此时可以把酒店的经纬度数据设置为地理数据类型。该类型的定义需要在mapping中指定目标字段的数据类型为geo_point类型
		  

	- 3.2.5 动态映射

	  当字段没有定义时，ES可以根据写入的数据自动定义该字段的类型，这种机制叫作动态映射。其实，在前面的章节中我们已经用到了ES的动态映射机制。在介绍数组类型和对象类型时提到，这两种类型都不需要用户提前定义，ES将根据写入的数据自动创建mapping中对应的字段并指定类型。对于基本类型，如果字段没有定义，ES在将数据存储到索引时会进行自动映射
	  

	- 3.2.6 多字段

	  针对同一个字段，有时需要不同的数据类型，这通常表现在为了不同的目的以不同的方式索引相同的字段。例如，在订单搜索系统中，既希望能够按照用户姓名进行搜索，又希望按照姓氏进行排列，可以在mapping定义中将姓名字段先后定义为text类型和keyword类型，其中，keyword类型的字段叫作子字段，这样ES在建立索引时会将姓名字段建立两份索引，即text类型的索引和keyword类型的索引。
	  

- 3.3 文档操作

	- 3.3.1 单条写入文档
	- 3.3.2 批量写入文档

	  请求体的第一行表示写入的第一条文档对应的元数据，其中，index_name表示写入的目标索引，第2行表示数据体，第3行表示写入的第二条文档对应的元数据，第4行表示数据体
	  

	- 3.3.3 更新单条文档

	  除了普通的update功能，ES还提供了upsert。upsert即是update和insert的合体字，表示更新/插入数据。如果目标文档存在，则执行更新逻辑；否则执行插入逻辑。以下DSL演示了upsert的应用：
	  

	- 3.3.4 批量更新文档

	- 3.3.5 根据条件更新文档

	- 3.3.6 删除单条文档

	- 3.3.7 批量删除文档

	- 3.3.8 根据条件删除文档

### 第4章 丰富的搜索功能
（阅读时间：1小时）

- 4.1 搜索辅助功能

  ES为用户提供了丰富的搜索功能：既有基本的搜索功能，又有搜索建议功能；既有常用的普通类型的匹配功能，又有基于地理位置的搜索功能；既提供了分页搜索功能，又提供了搜索的调试分析功能。本章将带领读者一起学习ES的搜索匹配知识，为后续的搜索实战打下基础
  

	- 4.1.1 指定返回的字段

	  每个命中文档的_source结构体中只包含指定的city和title两个字段的数据
	  

	- 4.1.2 结果计数

	  为提升搜索体验，需要给前端传递搜索匹配结果的文档条数，即需要对搜索结果进行计数。针对这个要求，ES提供了_count API功能，在该API中，用户提供query子句用于结果匹配，ES会返回匹配的文档条数。
	  
	  由结果可知，ES不仅返回了匹配的文档数量（值为3），并且还返回了和分片相关的元数据，如总共扫描的分片个数，以及成功、失败、跳过的分片个数等。
	  

	- 4.1.3 结果分页

	  在实际的搜索应用中，分页是必不可少的功能。在默认情况下，ES返回前10个搜索匹配的文档。用户可以通过设置from和size来定义搜索位置和每页显示的文档数量，from表示查询结果的起始下标，默认值为0，size表示从起始下标开始返回的文档个数，默认值为10
	  
	  注意，如果将配置修改得很大，一定要有足够强大的硬件作为支撑。
	  
	  作为一个分布式搜索引擎，一个ES索引的数据分布在多个分片中，而这些分片又分配在不同的节点上。一个带有分页的搜索请求往往会跨越多个分片，每个分片必须在内存中构建一个长度为from+size的、按照得分排序的有序队列，用以存储命中的文档。然后这些分片对应的队列数据都会传递给协调节点，协调节点将各个队列的数据进行汇总，需要提供一个长度为number_of_shards*（from+size）的队列用以进行全局排序，然后再按照用户的请求从from位置开始查找，找到size个文档后进行返回。
	  
	  基于上述原理，ES不适合深翻页。什么是深翻页呢？简而言之就是请求的from值很大。
	  
	  当深翻页的请求过多时会增加各个分片所在节点的内存和CPU消耗。尤其是协调节点，随着页码的增加和并发请求的增多，该节点需要对这些请求涉及的分片数据进行汇总和排序，过多的数据会导致协调节点资源耗尽而停止服务。
	  
	  作为搜索引擎，ES更适合的场景是对数据进行搜索，而不是进行大规模的数据遍历。一般情况下，只需要返回前1000条数据即可，没有必要取到10 000条数据。如果确实有大规模数据遍历的需求，可以参考使用scroll模式或者考虑使用其他的存储引擎
	  

	- 4.1.4 性能分析

	  在使用ES的过程中，有的搜索请求的响应可能比较慢，其中大部分的原因是DSL的执行逻辑有问题。ES提供了profile功能，该功能详细地列出了搜索时每一个步骤的耗时，可以帮助用户对DSL的性能进行剖析。开启profile功能只需要在一个正常的搜索请求的DSL中添加"profile":"true"即可
	  

	- 4.1.5 评分分析

	  在使用搜索引擎时，一般都会涉及排序功能。如果用户不指定按照某个字段进行升序或者降序排列，那么ES会使用自己的打分算法对文档进行排序。有时我们需要知道某个文档具体的打分详情，以便于对搜索DSL问题展开排查。ES提供了explain功能来帮助使用者查看搜索时的匹配详情
	  

- 4.2 丰富的搜索匹配功能

  针对不同的数据类型，ES提供了很多搜索匹配功能：既有进行完全匹配的term搜索，也有按照范围匹配的range搜索；既有进行分词匹配的match搜索，也有按照前缀匹配的suggest搜索
  

	- 4.2.1 查询所有文档

	  使用match_all查询文档时，ES不对文档进行打分计算，默认情况下给每个文档赋予1.0的得分。用户可以通过boost参数设定该分值
	  
	  在Java客户端中进行查询时，可以调用QueryBuilders.matchAllQuery()方法新建一个match_all查询，并且通过boost()方法设置boost值。构建完term查询后，调用searchSource Builder.query()方法设置查询条件。
	  

	- 4.2.2 term级别查询

		- 1.term查询
		- 2.terms查询
		- 3.range查询

		  range查询用于范围查询，一般是对数值型和日期型数据的查询。使用range进行范围查询时，用户可以按照需求中是否包含边界数值进行选项设置
		  

		- 4.exists查询

	- 4.2.3 布尔查询

	  布尔查询是常用的复合查询，它把多个子查询组合成一个布尔表达式，这些子查询之间的逻辑关系是“与”，即所有子查询的结果都为true时布尔查询的结果才为真。布尔查询还可以按照各个子查询的具体匹配程度对文档进行打分计算。
	  

		- 1.must查询
		- 2.should查询

		  当查询中包含should查询时，表示当前查询为“或”查询。命中的文档可以匹配该查询中的一个或多个子查询的结果，并且ES会将该查询与文档的匹配程度加入总得分里。should查询包含一个数组，可以把其他的term级别的查询及布尔查询放入其中。
		  

		- 3.must not查询

		  当查询中包含must not查询时，表示当前查询为“非”查询。命中的文档不能匹配该查询中的一个或多个子查询的结果，ES会将该查询与文档的匹配程度加入总得分里。must not查询包含一个数组，可以把其他term级别的查询及布尔查询放入其中。
		  

		- 4.filter查询

		  filter查询即过滤查询，该查询是布尔查询里非常独特的一种查询。其他布尔查询关注的是查询条件和文档的匹配程度，并按照匹配程度进行打分；而filter查询关注的是查询条件和文档是否匹配，不进行相关的打分计算，但是会对部分匹配结果进行缓存。
		  

	- 4.2.4 filter查询原理

	- 4.2.5 Constant Score查询

	  如果不想让检索词频率TF（Term Frequency）对搜索结果排序有影响，只想过滤某个文本字段是否包含某个词，可以使用Constant Score将查询语句包装起来。
	  

		- 搜索停车场

	- 4.2.6 Function Score查询

	  当使用ES进行搜索时，命中的文档默认按照相关度进行排序。有些场景下用户需要干预该“相关度”，此时就可以使用Function Score查询。使用时，用户必须定义一个查询以及一个或多个函数，这些函数为每个文档计算一个新分数。
	  

	- 4.2.7 全文搜索

	  不同于结构化查询，全文搜索首先对查询词进行分析，然后根据查询词的分词结果构建查询。这里所说的全文指的是文本类型数据（text类型），默认的数据形式是人类的自然语言，如对话内容、图书名称、商品介绍和酒店名称等。结构化搜索关注的是数据是否匹配，全文搜索关注的是匹配的程度；结构化搜索一般用于精确匹配，而全文搜索用于部分匹配。
	  

		- 1.match查询

		  这是因为在默认情况下，match查询使用的是标准分词器。该分词器比较适用于英文，如果是中文则按照字进行切分，因此默认的分词器不适合做中文搜索
		  

		- 2.multi_match查询

		  有时用户需要在多个字段中查询关键词，除了使用布尔查询封装多个match查询之外，可替代的方案是使用multi_match。可以在multi_match的query子句中组织数据匹配规则，并在fields子句中指定需要搜索的字段列表。
		  

		- 3.match_phrase查询

		  match_phrase用于匹配短语，与match查询不同的是，match_phrase用于搜索确切的短语或邻近的词语。假设在酒店标题中搜索“文雅酒店”，希望酒店标题中的“文雅”与“酒店”紧邻并且“文雅”在“酒店”前面
		  

	- 4.2.8 基于地理位置查询

	  随着互联网+的热门，越来越多的传统行业将全部或者部分业务转移到互联网上，其中不乏一些和地理位置强相关的行业。基于地理位置的搜索功能，大大提升了人们的生活和工作效率。例如，外出旅行时，只需要用手机打开订酒店的应用软件，查找附近心仪的酒店下单即可；又或者打车行业，人们不用在寒冷的户外去拦截出租车，只需要在室内打开手机里的打车App定位到当前位置，然后确定目的地，系统就可以为附近的车辆派发订单。
	  
	  幸运的是，ES为用户提供了基于地理位置的搜索功能。它主要支持两种类型的地理查询：一种是地理点（geo_point），即经纬度查询，另一种是地理形状查询（geo_shape），即支持点、线、圆形和多边形查询等。
	  
	  geo_distance查询方式需要用户指定一个坐标点，在指定距离该点的范围后，ES即可查询到相应的文档
	  
	  geo_shape查询提供的是矩形内的搜索，需要用户给出左上角的顶点地理坐标和右下角的顶点地理坐标。
	  

	- 4.2.9 搜索建议

	  搜索建议，顾名思义，即在用户输入搜索关键词的过程中系统进行自动补全，用户可以根据自己的需求单击搜索建议的内容直接进行搜索。在搜索时，用户每输入一个字符，前端就需要向后端发送一次查询请求对匹配项进行查询，因此这种场景对后端响应速度的要求比较高。通过协助用户进行搜索，可以避免用户输入错误的关键词，引导用户使用更合适的关键词，提升用户的搜索体验和搜索效率。
	  
	  那么类似的功能在ES中是如何实现呢？答案就是ES的搜索建议查询。对于以上应用来说，ES中的Completion Suggester是比较合适的。为了使用Completion Suggester，其对应的字段类型需要定义为completion类型。
	  
	  和普通搜索不同的是，搜索建议的结果不是封装在hits中，而是单独封装在suggest中。在suggest.hotel_zh_sug.options中可以看到每一个候选集的文档信息。
	  
	  需要注意的是，ES提供的Completion Suggester功能使用的索引结构不是倒排索引，而是在内存中构建FST（Finite StateTransducers）。构建该数据结构是有比较大的内存存储成本的，因此在生产环境中向索引中添加数据时一定要关注ES节点的内存消耗，避免数据量过大造成ES节点内存耗尽从而影响集群服务。
	  

		- FST

- 4.3 按字段值排序

  在默认情况下，ES对搜索结果是按照相关性降序排序的。有时需要按照某些字段的值进行升序或者降序排序。
  
  在默认情况下，ES对搜索结果是按照相关性降序排序的。有时需要按照某些字段的值进行升序或者降序排序。例如在酒店搜索应用软件中，除了可以按照综合排序外，还可以按照价格、销量、评论数、距离进行升/降序排序。之所以提供这样的功能，是因为存在多种不同的心态促使用户并不只想按照关键词匹配对结果进行浏览。用户单击价格进行升序或降序排列，很可能说明该用户对酒店价格比较敏感；用户按照销量或评论数进行升序或降序排列，很可能说明用户有一些从众心理，希望通过销量或评论数来评估大众对该酒店是否看好，从而筛选心仪的酒店；用户按照距离进行排序，很可能是该用户需要找到距离匹配的酒店
  

	- 4.3.1 按普通字段值排序

	  使用sort子句对字段值进行排序时需要指定排序的字段。ES默认是按照字段值进行升序排序，可以设置order参数为asc或desc，指定按照字段值进行升序或者降序排序。
	  

	- 4.3.2 按地理距离排序

	  前面我们介绍了ES提供的基于地理位置的查询功能，使用geo_distance查询，配合sort可以指定另一种排序规则，即按照文档坐标与指定坐标的距离对结果进行排序。使用时，需要在sort内部指定排序名称为geo_distanc，并指定目的地坐标。除了可以指定升序或者降序排列外，还可以指定排序结果中sort子句中的距离的计量单位，默认值为km即千米。在进行距离计算时，系统默认使用的算法为arc，该算法的特点是计算精准但是耗费时间较长，用户可以使用distance_type参数选择另一种计算速度快但经度略差的算法，名称为plane
	  

		- arc

## 第2篇 Elasticsearch提高

### 第5章 文本搜索

- 5.1 文本搜索简介

	- 5.1.1 文本的索引建立过程
	- 5.1.2 文本的搜索过程

- 5.2 分析器简介

	- 5.2.1 字符过滤器
	- 5.2.2 分词器
	- 5.2.3 分词过滤器

- 5.3 分析器的使用

	- 5.3.1 测试分析API
	- 5.3.2 内置分析器
	- 5.3.3 索引时使用分析器
	- 5.3.4 搜索时使用分析器
	- 5.3.5 自定义分析器

- 5.4 中文分析器

	- 5.4.1 中文分词介绍
	- 5.4.2 IK分析器
	- 5.4.3 HanLP分析器

- 5.5 使用同义词

	- 5.5.1 建立索引时使用同义词
	- 5.5.2 查询时使用同义词

- 5.6 使用停用词

	- 5.6.1 使用停用词过滤器
	- 5.6.2 在内置分析器中使用停用词
	- 5.6.3 在IK分析器中使用停用词
	- 5.6.4 在HanLP分析器中使用停用词

- 5.7 拼音搜索

	- 5.7.1 拼音分析器插件的安装
	- 5.7.2 拼音分析器插件的使用

- 5.8 高亮显示搜索

	- 5.8.1 初步使用高亮显示搜索
	- 5.8.2 选择高亮显示搜索策略
	- 5.8.3 在Java客户端中进行高亮显示搜索

- 5.9 拼写纠错

	- 5.9.1 使用Elasticsearch进行拼写纠错
	- 5.9.2 更精准的拼写纠错

### 第6章 搜索排序

- 6.1 相关性排序

	- 6.1.1 TF-IDF模型
	- 6.1.2 向量空间模型
	- 6.1.3 BM25算法简介
	- 6.1.4 BM25实例解析
	- 6.1.5 BM25参数调节
	- 6.1.6 分布式场景对排序的影响
	- 6.1.7 使用其他相关性算法

- 6.2 查询时设置权重

	- 6.2.1 查询时boost参数的设置
	- 6.2.2 在Java客户端中使用boost参数
	- 6.2.3 boosting查询
	- 6.2.4 在Java客户端中使用boosting查询

- 6.3 Function Score查询简介

	- 6.3.1 简单函数
	- 6.3.2 函数计算关系
	- 6.3.3 衰减函数

- 6.4 Script Score查询简介

	- 6.4.1 Painless简介
	- 6.4.2 在Script Score中使用Painless
	- 6.4.3 使用数组和集合
	- 6.4.4 使用文档数据
	- 6.4.5 向脚本传参
	- 6.4.6 在Script Score中使用函数
	- 6.4.7 在Java客户端中使用Script Score
	- 6.4.8 练习Painless

- 6.5 二次打分

	- 6.5.1 二次打分简介
	- 6.5.2 使用示例
	- 6.5.3 在Java客户端中使用二次打分

### 第7章 聚合

- 7.1 聚合指标

	- 7.1.1 常见的统计指标
	- 7.1.2 空值处理

- 7.2 桶聚合

	- 7.2.1 单维度桶聚合
	- 7.2.2 多维度桶嵌套聚合
	- 7.2.3 地理距离聚合

- 7.3 聚合方式

	- 7.3.1 直接聚合
	- 7.3.2 先查询再聚合
	- 7.3.3 前过滤器
	- 7.3.4 后过滤器

- 7.4 聚合排序

	- 7.4.1 按文档计数排序
	- 7.4.2 按聚合指标排序
	- 7.4.3 按分组key排序

- 7.5 聚合分页

	- 7.5.1 Top hits聚合
	- 7.5.2 Collapse聚合

## 第3篇 Elasticsearch实战

### 第8章 搜索实战

- 8.1 项目简介

	- 8.1.1 背景简介
	- 8.1.2 搜索建议功能简介
	- 8.1.3 搜索功能简介
	- 8.1.4 排序功能简介

- 8.2 技术方案

	- 8.2.1 总体架构设计
	- 8.2.2 组件简介
	- 8.2.3 搜索建议方案
	- 8.2.4 匹配方案
	- 8.2.5 排序方案

- 8.3 搜索建议功能的实现

	- 8.3.1 索引创建
	- 8.3.2 后端服务
	- 8.3.3 索引初始化
	- 8.3.4 搜索建议服务

- 8.4 搜索功能的实现

	- 8.4.1 项目创建
	- 8.4.2 索引创建
	- 8.4.3 数据初始化
	- 8.4.4 整体工作
	- 8.4.5 获取总页数
	- 8.4.6 查询构建
	- 8.4.7 结果处理和封装
	- 8.4.8 对搜索排序进行打分

- 8.5 前端功能的实现

	- 8.5.1 项目创建
	- 8.5.2 请求转发
	- 8.5.3 搜索建议交互
	- 8.5.4 搜索交互

*XMind - Trial Version*