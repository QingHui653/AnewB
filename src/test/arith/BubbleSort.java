package test.arith;

import java.util.Arrays;

/**
 * 冒泡排序
 * 除了名字好听无卵用
 * 复杂度O(N2)
 * @author woshizbh
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = { 11, 3, 5, 6, 0, 6, 7, 6, 3, 8 };
		for (int k = 0; k < arr.length-1; k++) {   // N
			for (int i = 0; i < arr.length - 1; i++) {  //N
				if (arr[i] > arr[i + 1]) {
					int sort = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = sort;
				}
			}
		}
		System.out.println(" " + Arrays.toString(arr));
	}
}
