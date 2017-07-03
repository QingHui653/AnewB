package test.core.dataStructure.node;


public class treeNode<T> {
	public T t;
	
	public treeNode<T> left;
	
	public treeNode<T> right;
	
	public treeNode(T data) {
		this.t = data;
	}
}