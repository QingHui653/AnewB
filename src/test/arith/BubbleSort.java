package test.arith;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * 冒泡排序
 * 平均复杂度O(N^2) 最好O(N) 最坏O(N^2) 空间复杂度O(1) 内排序 稳定
 * @author woshizbh
 * http://gold.xitu.io/post/57dcd394a22b9d00610c5ec8
 */
public class BubbleSort {

	public static void main(String[] args) {
		BubbleSort b= new BubbleSort();
		int[] arr = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		int[] c1=b.BubbleSort1(arr);
		int[] c2=b.BubbleSort2(arr);
		int[] c3=b.BubbleSort3(arr);
		System.out.println("冒泡" + Arrays.toString(c1));
		System.out.println("改进加标示的冒泡" + Arrays.toString(c2));
		System.out.println("双向加标签的冒泡" + Arrays.toString(c3));
	}
	
	public int[] BubbleSort1(int[] arr) {  //速度最慢
		int len=arr.length;
		for (int i = 0; i < len; i++) {
	        for (int j = 0; j < len - 1 - i; j++) {
	            if (arr[j] > arr[j+1]) {        //相邻元素两两对比
	                int temp = arr[j+1];        //元素交换
	                arr[j+1] = arr[j];
	                arr[j] = temp;
	            }
	        }
	    }
		return arr;
	}
	
	public int[] BubbleSort2(int[] arr) {
		int i=arr.length-1;
		while (i>0) {
			int pos=0; 	//标签从0开始
			for (int j = 0; j < i; j++) {
				if(arr[j]>arr[j++]){  
					pos=j;				//第一次已经将最大或最小已移至边缘,所以不在计算这个值
					int temp = arr[j+1];//交换
	                arr[j+1] = arr[j];
	                arr[j] = temp;
				}
			}
			i=pos;           			//第一次已经将最大或最小已移至边缘,所以不在计算这个值
		}
		return arr;
	}
	
	public int[] BubbleSort3(int[] arr) {
		int low=0;
		int high=arr.length-1;
		int tmp,j;
		while (low<high) {
			for (j = low; j<high; j ++)  //正向冒泡,找到最大者
				if(arr[j]<arr[j++]){
					tmp = arr[j];
	                arr[j] = arr[j + 1];
	                arr[j + 1] = tmp;
				}
			--high; //修改high值, 前移一位
			for (j = high; j > low; --j) //反向冒泡,找到最小者
		            if (arr[j] < arr[j - 1]) {
		                tmp = arr[j];
		                arr[j] = arr[j - 1];
		                arr[j - 1] = tmp;
		            }
		     ++low; //修改low值,后移一位
		}
		return arr;
	}
}
