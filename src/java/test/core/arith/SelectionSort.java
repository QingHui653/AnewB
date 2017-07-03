package test.core.arith;

import java.util.Arrays;

/**
 * 选择排序,
 * 选个最小或最大位置,然后在选择最小或(最大的)存入 这个为有序区
 * 然后从无序区第一位开始选最小或(最大)存入,将这从无序移入有序区
 * @author woshizbh
 *
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr={3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		
		int len =arr.length;
		int minIndex,temp;
		
		for (int i = 0; i < len; i++) {
			minIndex=i;
			for (int j = i+1; j < len; j++) {
				if (arr[j] < arr[minIndex]) {     //寻找最小的数
	                minIndex = j;                 //将最小数的索引保存
	            }
			}
			temp = arr[i];
	        arr[i] = arr[minIndex];
	        arr[minIndex] = temp;
		}
		System.out.println("选择排序"+Arrays.toString(arr));
	}
}
