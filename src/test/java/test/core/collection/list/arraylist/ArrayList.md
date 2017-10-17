package test.core.collection.list.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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
 *内存溢出：程序在申请内存时，没有足够的内存空间供其使用
      内存泄露：程序在申请内存后，无法释放已申请的内存空间，一次内存泄露危害可以忽略，但内存泄露堆积后果很严重，无论多少内存,迟早会被占光
 */
public class ArrayListModel<E> {

	//底层为数组Object[]    transient 阻止序列化
	private transient Object[] elementData;
	//数组的长度  ：注意区分长度（当前数组已有的元素数量）和容量（当前数组可以拥有的元素数量）的概念
	private int size;
	//数组允许的最大长度  ; 超出报内存溢出
	//Integer.MAX_VALUE-8 根本作用在于只是为了避免一些机器内存溢出，是否-8其实并不重要，实际最大长度还是Integer.MAX_VALUE
	//之所以要-8是因为有些版本的虚拟机会保留8个字节长度的header，下面以HotSpot为例
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

	/**
	  * Increases the capacity to ensure that it can hold at least the
	  * number of elements specified by the minimum capacity argument.
	  * 当数组长度加1超过原容量时，会自动扩容 (size + 1)
	  * @param minCapacity the desired minimum capacity
	  */
	private void grow(int minCapacity) {
	    // overflow-conscious code 溢出敏感
	    int oldCapacity = elementData.length;
	    //区别于HashMap的2倍扩容，ArratList选择是的1.5倍扩容(向上取整)
	    //等同于 (int)Math.floor(oldCapacity*1.5) 或 (oldCapacity * 3)/2 + 1
	    //PS:涉及到2次幂的操作推荐直接使用位运算，尤其是设置2次幂容量场景 eg : new ArrayList(x << 1)
	    int newCapacity = oldCapacity + (oldCapacity >> 1);
	    if (newCapacity - minCapacity < 0)
	        newCapacity = minCapacity; //最小容量设置
	    if (newCapacity - MAX_ARRAY_SIZE > 0)
	        newCapacity = hugeCapacity(minCapacity); //最大容量设置
	    // minCapacity is usually close to size, so this is a win:
	    //新建一个原数组的拷贝，并修改原数组，指向这个新建数组；原数组自动抛弃，等待GC回收
	    elementData = Arrays.copyOf(elementData, newCapacity);
	}

	/**
	  * 最大容量设置
	  */
	private static int hugeCapacity(int minCapacity) {
	    if (minCapacity < 0) // overflow 内存溢出
	        throw new OutOfMemoryError();
	    //注意：最大容量是 Integer.MAX_VALUE 而不是 MAX_ARRAY_SIZE=Integer.MAX_VALUE-8
	    return (minCapacity > MAX_ARRAY_SIZE) ?Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	/**
	  * Returns the element at the specified position in this list.
	  * 返回指定下标的元素
	  * @param  index index of the element to return
	  * @return the element at the specified position in this list
	  * @throws IndexOutOfBoundsException {@inheritDoc}
	  */
	public E get(int index) {
	    rangeCheck(index);//下标边界校验
	    return elementData(index);//直接调用数组的下标方法
	}
	/**
	  * 数组访问方法
	  */
	@SuppressWarnings("unchecked")
	E elementData(int index) {
	    return (E) elementData[index];
	}

	/**
	  * 当index>=数组长度时，抛出`IndexOutOfBoundsException` 下标越界
	  * 当index为负数时，抛出`ArrayIndexOutOfBoundsException` 数组下标越界
	  */
	private void rangeCheck(int index) {
	    if (index >= size)
	        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}


	/**
	  *
	  * 移除指定下标元素，同时大于该下标的所有数组元素统一左移一位
	  */
	public E remove(int index) {
	    rangeCheck(index);//下标边界校验
	    E oldValue = elementData(index);//获取当前坐标元素
	    fastRemove(int index);//这里我修改了一下源码，改成直接用fastRemove方法，逻辑不变
	    return oldValue;//返回原数组元素
	}
	/**
	  * 直接移除某个元素：
	  *     当该元素不存在，不会发生任何变化
	  *     当该元素存在且成功移除时，返回true，否则false
	  *     当有重复元素时，只删除第一次出现的同名元素 ：
	  *        例如只移除第一次出现的null（即下标最小时出现的null）
	  */
	public boolean remove(Object o) {
	    //按元素移除时都需要按顺序遍历找到该值，当数组长度过长时，相当耗时
	    if (o == null) {//ArrayList允许null，需要额外进行null的处理（只处理第一次出现的null）
	        for (int index = 0; index < size; index++)
	            if (elementData[index] == null) {
	                fastRemove(index);
	                return true;
	            }
	    } else {
	        for (int index = 0; index < size; index++)
	            if (o.equals(elementData[index])) {
	                fastRemove(index);
	                return true;
	            }
	    }
	    return false;
	}

	/*
     * Private remove method that skips bounds checking and does not return the value removed.
     * 私有方法，除去下标边界校验以及不返回移除操作的结果
     */
    private void fastRemove(int index) {
        modCount++;//remove操作属于结构性改动，modCount计数+1
        int numMoved = size - index - 1;//需要左移的长度
        if (numMoved > 0)
            //大于该下标的所有数组元素统一左移一位
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null; // Let gc do its work 长度-1，同时加快gc
    }

    /**
     * Removes all of the elements from this list.  The list will be empty after this call returns.
     * 移除所有元素
     */
   public void clear() {
       modCount++;
       // Let gc do its work
       for (int i = 0; i < size; i++)
           elementData[i] = null;//每个元素设置为null，加速gc
       size = 0;//长度为0
   }
//迭代
   //结构性变更操作必须使用迭代器
   Iterator iter = list.iterator();
   while (iter.hasNext()) {
       Integer o = (Integer)iter.next();
   }

   //快速读取时推荐使用随机访问方式
   for (int i=0; i < list.size(); i++) {
       Integer o = (Integer)list.get(i);
   }

   for (Object o:list) { }

//序列化
   /**
    * 直接将size和element写入ObjectOutputStream
    *
    */
  private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException{
      // Write out element count, and any hidden stuff 序列化操作需要校验modCount
      int expectedModCount = modCount;
      s.defaultWriteObject();// 获取数组元素的Class信息
      // Write out array length 获取缓存数组长度
      s.writeInt(elementData.length);
      // Write out all elements in the proper order. 依次写入
      for (int i=0; i<size; i++)
          s.writeObject(elementData[i]);
      if (modCount != expectedModCount) {
          throw new ConcurrentModificationException();
      }
  }

  /**
            反序列化
  * 从ObjectInputStream获取size和element，再恢复到elementData
  */
	private void readObject(java.io.ObjectInputStream s)
	    throws java.io.IOException, ClassNotFoundException {
	    // Read in size, and any hidden stuff 获取数组元素的Class信息和数组长度
	    s.defaultReadObject();
	    // Read in array length and allocate array 获取数组长度并分配一个该长度动态数组（Object类型）
	    int arrayLength = s.readInt();
	    Object[] a = elementData = new Object[arrayLength];
	    // Read in all elements in the proper order. 依次读出
	    for (int i=0; i<size; i++)
	        a[i] = s.readObject();
	}

为什么不直接用elementData来序列化，而采用上诉的方式来实现序列化呢?
原因在于elementData是一个缓存数组，通常会预留一些容量，等容量不足时再扩充容量，那么有些空间可能就没有实际存储元素，采用上诉的方式来实现序列化时，就可以保证只序列化实际存储的那些元素，而非整个数组，从而节省空间和时间
重点：根本是要区别size和capacity的概念，同时也是优化代码的一种策略

为什么调用ArrayList中的 toArray() 可能遇到过抛出java.lang.ClassCastException异常？
ArrayList提供了2个toArray()函数：
Object[] toArray(); -- 调用 toArray() 函数会抛出java.lang.ClassCastException异常
<T> T[] toArray(T[] contents) -- 调用 toArray(T[] contents) 能正常返回 T[]
toArray() 会抛出异常是因为 toArray() 返回的是 Object[] 数组，将 Object[] 转换为其它类型(如将Object[]转换为的Integer[])则会抛出java.lang.ClassCastException异常，因为Java不支持向下转型
解决该问题的办法是调用 <T> T[] toArray(T[] contents) ， 而不是 Object[] toArray()

推荐方案
 	public static Integer[] toArray(ArrayList<Integer> arrayList) {
    	//调用 <T> T[] toArray(T[] contents),而不是 Object[] toArray()
    	Integer[] newArrayList = (Integer[])arrayList.toArray(new Integer[0]);
    	return newArrayList;
	}
}
