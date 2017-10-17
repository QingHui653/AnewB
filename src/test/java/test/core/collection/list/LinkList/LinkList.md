* 继承于AbstractSequentialList的双向链表，可以被当作堆栈、队列或双端队列进行操作
* 有序，非线程安全的双向链表，默认使用尾部插入法
* 适用于频繁新增或删除场景，频繁访问场景请选用ArrayList
* 插入和删除时间复杂为O(1)，其余最差O(n)
* 由于实现Deque接口，双端队列相关方法众多，会专门来讲，这里不多加详述
* 此版本基于JDK1.7

	`public class LinkedList<E>
	extends AbstractSequentialList<E>
	implements List<E>, Deque<E>, Cloneable, java.io.Serializable`

