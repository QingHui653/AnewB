package test.core.dataStructure.node;


public class Test {
	
	@org.junit.Test
	public void test() {
		/**
		 * 自己实现的二叉树  ，bug好多。。。
		 */
		tree<Object> tree =new tree<Object>();
		
		tree.insertNode(5);
		tree.insertNode(3);
		tree.insertNode(7);
		tree.insertNode(2);
		tree.insertNode(4);
		
		System.out.println("tree"+tree.toString());
	}
}
