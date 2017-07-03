package test.core.arith;

import java.util.Arrays;


/**
 *  最简单的桶排序
 *   O(2*(m+n))。我们在说时间复杂度时候可以忽略较小的常数，最终桶排序的时间复杂度为 O(m+n)
 * @author woshizbh
 *
 */
public class BucketSort {

	public static void main(String[] args) {
		int[] arr=new int[5];   //M
		int[] a={4,2,2,1,3};
		System.out.println("初始化  "+Arrays.toString(arr));
		for (int i : a) {		//N
			arr[i]=arr[i]+1;
		}
		System.out.println("放入木桶 "+Arrays.toString(arr));
		
		for (int i = 0; i < 5; i++) {  //  依次倒出木桶中的数量,木桶已经是排好序的    //M
			for (int j = 0; j < arr[i]; j++) {	//	木桶的数量,木桶有两个,即输出两次//N
				System.out.print(i+" ");
			}
		}
	}
}
