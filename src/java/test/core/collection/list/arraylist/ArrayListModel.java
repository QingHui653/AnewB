package test.core.collection.list.arraylist;

import java.util.Arrays;
import java.util.Collection;

/**
 *  非线程安全的动态数组（Array升级版），支持动态扩容
            实现 List 接口、底层使用数组保存所有元素，其操作基本上是对数组的操作，允许null值
	实现了 RandmoAccess 接口，提供了随机访问功能
	线程安全可参见Vector
	适用于访问频繁场景，频繁插入或删除场景请选用linkedList
	增删 使用 迭代器
 * @author woshizbh
 *
 */

/**
 * 继承 AbstractList，实现了 List，它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能
实现 RandmoAccess 接口，实现快速随机访问：通过元素的序号快速获取元素对象
实现 Cloneable 接口，重写 clone()，能被克隆（浅拷贝）
实现 java.io.Serializable 接口，支持序列化
 * @param <E>
 *
 */
public class ArrayListModel<E> {

	//底层为数组Object[]    transient 阻止序列化
	private transient Object[] elementData;
	//数组的长度  ：注意区分长度（当前数组已有的元素数量）和容量（当前数组可以拥有的元素数量）的概念
	private int size;
	//数组允许的最大长度  ; 超出报内存溢出
	private static final int MAX_ARRAY_SIZE =Integer.MAX_VALUE-8;
//构造器
	//创建一个指定容量的空列表
	public ArrayListModel(int initialCapacity) {
		super();
		if(initialCapacity<0)
			throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
		this.elementData=new Object[initialCapacity];
	}
	//默认容量为10
	public ArrayListModel() {
		this(10);
	}
	//接受一个Collection对象直接转换为ArrayList
	public ArrayListModel(Collection<? extends E> c){
		elementData = c.toArray();//获取底层动态数组
	    size = elementData.length;//获取底层动态数组的长度
	    // c.toArray might (incorrectly) not return Object[] (see 6260652)
	    if (elementData.getClass() != Object[].class)
	        elementData = Arrays.copyOf(elementData, size, Object[].class);
	}
//存储
	//ArrayList在新增元素中，会执行一系列额外操作(包括扩容、各类异常校验 - 基本都跟长度相关)
	//ArrayList使用尾插入法，新增元素插入到数组末尾；支持动态扩容
	//add()常见的异常主要包括：IndexOutOfBoundsException、OutOfMemoryError

	/*
	 * 使用尾插入法，新增元素插入到数组末尾
     * 由于错误检测机制使用的是抛异常，所以直接返回true
	 */
	public boolean add(E e) {
	    //调整容量，修改elementData数组的指向; 当数组长度加1超过原容量时，会自动扩容
	    ensureCapacityInternal(size + 1);  // Increments modCount!! add属于结构性修改
	    elementData[size++] = e;//尾部插入，长度+1
	    return true;
	}

	/**
	 * 支持插入一个新元素到指定下标
	 * 该操作会造成该下标之后的元素全部后移（使用时请慎重，避免数组长度过大）
	 *
	 * 不推荐频繁插入或删除场景的原因在于其执行add或者remove方法时会调用非常耗时的System.arraycopy方法
	 * 频繁插入或删除场景请选用LinkedList
	 */

	public void add(int index, E element) {
	    //下标边界校验，不符合规则 抛出 `IndexOutOfBoundsException`
	    rangeCheckForAdd(index);
	    //调整容量，修改elementData数组的指向; 当数组长度加1超过原容量时，会自动扩容
	    ensureCapacityInternal(size + 1);  // Increments modCount!!
	    //注意是在原数组上进行位移操作，下标为 index+1 的元素统一往后移动一位
	    System.arraycopy(elementData, index, elementData, index + 1,size - index);
	    elementData[index] = element;//当前下标赋值
	    size++;//数组长度+1
	}
	
	/**
	  * A version of rangeCheck used by add and addAll.
	  * 下标边界校验 ： 下标必须为小于等于数组长度的正整数（Int）
	  * 不符合规则 抛出 `IndexOutOfBoundsException` ，即 下标越界异常
	  */
	private void rangeCheckForAdd(int index) {
	    //下标超过数组长度 or 下标 < 0 
	    if (index > size || index < 0)
	         throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	
	/**
	  * 调整容量，修改elementData数组的指向
	  */
	private void ensureCapacityInternal(int minCapacity) {
	    modCount++; //add操作属于结构性变动，modCount计数+1
	    // overflow-conscious code 溢出敏感
	    if (minCapacity - elementData.length > 0)
	        grow(minCapacity);//自动扩容
	}
}
