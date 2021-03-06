# 《趣学数据结构》

## 第10 章 高级数据结构

### 10.1 并查集

### 10.2 优先队列

### 10.3 B-树

- 概念

	- 1. 如何提高二叉树搜索效率？

		- 二叉搜索树的搜索效率和树高成正比关系，通过减少二叉搜索树的高度，可以提高搜索效率

	- 2. 什么多路平衡二叉树？

		- 如果一个节点不限于存储一个关键字，就可以包含多个关键字和多个子树，既保持二叉搜索树的特性，又具有平衡性，这样的搜索树称为多路平衡搜索树

	- 3. 多路平衡二叉树的应用场景？

		- 多路平衡搜索树主要用于大规模数据的分级存储搜索
		- 使用方式：因此可以用“大节点”代替多个单个节点，一个“大节点”包含多个连续存储的数据

	- 4. 一个“大节点”到底包含多少个数据元素合适呢？

		- 主要取决于不同外存的批量访问特性

	- 5. B树的特性?

		- 1）每个节点最多有m棵子树。
		- 2）根节点至少有两棵子树。
		- 3）内部节点（除根和叶子之外的节点）至少有[插图]棵子树。
		- 4）终端节点（叶子）在同一层上，并且不带信息（空指针），通常称为失败节点。
		- 5）非终端节点的关键字个数比子树个数少1。

	- 6. B树的特点?

		- B-树具有平衡、有序、多路的特点

	- 7. 根节点至少有一个关键字和两棵子树，其他非终端节点关键字个数范围为[插图]，子树个数范围为[插图]
	- 8. 3阶B-树，其内部节点的子树个数2≤k≤3，所以又称为2-3树

- 10.3.1 树高与性能

	- 一棵含有n个关键字的m阶B-树最大高度是多少呢？

		- 1. m代表每个节点的子树个树
		- 1. 根节点至少有两棵子树
		- 2. 每个非叶子节点至少有[插图]棵子树，每个子树对应一个节点

- 10.3.2 查找

	- 1. B树和二叉查找树的区别？

		- B-树的查找和二叉搜索树的查找类似，不同的是需要从外存调入节点，然后在节点内查找（一个节点可能包含多个关键字）。

	- 2. 算法复杂度分析

		- 1. B树的查找时间

			- 1. 将节点从外存调入内存
			- 2. 在内存中当前节点查找

		- 2. m阶B-树查找的时间复杂度为O(logmn)

- 10.3.3 插入

	- 1. 上溢
	- 2. 算法复杂度分析

- 10.3.4 删除

	- 1. 删除逻辑
	- 2. 算法复杂度分析

### 10.4 B+树

- 概念

	- 1. B+树是B-树的变种，更适用于文件索引系统
	- 2. 叶子之间有连接
	- 3. 特性

		- 1）每个节点最多有m棵子树。
		- 2）根节点至少有两棵子树。
		- 3）内部节点（除根和叶子之外的节点）至少有[插图]棵子树。
		- 4）终端节点（叶子）在同一层上，并且不带信息（空指针），通常称为失败节点。
		- 5）非终端节点的关键字个数与子树个数相同。
		- 6）倒数第二层节点包含了全部的关键字，节点内部有序且节点间按升序顺序链接。
		- 7）所有的非终端节点只作为索引部分，节点中仅含子树中的最大（或最小）关键字。

- 10.4.1 查找

	- B+树支持两种方式的查找，可以利用t指针从树根向下索引查找，也可以利用r指针从最小关键字向后顺序查找。
	- 每次查找都要走一条从树根到叶子的路径，时间复杂度为树高O(logmn)

- 10.4.2 插入

	- 1. 上溢
	- 2. 事件复杂度

- 10.4.3 删除

	- 1. 上溢
	- 2. 事件复杂度

### 10.5 红黑树

### 10.6 高级数据结构学习秘籍

*XMind - Trial Version*