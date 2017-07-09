package test.core.dataStructure.node;

import org.junit.Test;

public class tree<T> {

	public treeNode<T> root;

	public tree() {
	}
	
	public void insertNode(T data) {
		treeNode<T> t = new treeNode<T>(data);
		insertNode(t);
	}
	
	public void insertNode(treeNode<T> data) {
		if(!t(data)){
			if(!left(data,root))
				right(data,root);
		}
	}
	
	public boolean t(treeNode<T> data) {
		if(root==null){
			root=data;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean left(treeNode<T> data,treeNode<T> root) {
		if(root.left!=null){
			if((Integer)data.t<(Integer)root.left.t){
				left(data,root.left);
			}else{
				return false;
			}
		}else if((Integer)data.t<(Integer)root.t){
			root.left=data;
			return true;
		}
		return false;
	}
	
	public boolean right(treeNode<T> data,treeNode<T> root) {
		if(root.right!=null){
			if((Integer)data.t>(Integer)root.right.t){
				right(data,root.right);
			}else{
				return false;
			}
		}else if((Integer)data.t>(Integer)root.t){
			root.right=data;
			return true;
		}
		return false;
	}
	
	public void removeNode() {

	}

	public void findNode() {

	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(root!=null){
			
		}
		return null;
	}
	
	public String help() {
		return null;
	}
	
	@Test
	public void test() {
		tree<Object> tree =new tree<Object>();
		
		tree.insertNode(5);
		
		System.out.println("tree"+tree.toString());
	}
}
