package test.core.arith;

import java.util.Arrays;

import org.junit.Test;

/**
 * 选择排序, 选个最小或最大位置,然后在选择最小或(最大的)存入 这个为有序区 然后从无序区第一位开始选最小或(最大)存入,将这从无序移入有序区
 * 从0位快速，(循环，找出小的和0替换，然后在继续找，找出最小的)，最小和0替换。
 * 游标右移一位，重复
 * 所有需要两个for循环
 * @author woshizbh
 *
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = { 5, 44, 38, 3, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };

		int len = arr.length;
		int minIndex, temp;

		for (int i = 0; i < len; i++) {
			minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[minIndex]) { // 寻找最小的数
					minIndex = j; // 将最小数的索引保存
				}
			}
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		System.out.println("选择排序" + Arrays.toString(arr));
	}

	@Test
	public void testSort() {
		int[] arr = { 5, 44, 38, 3, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };

		int len = arr.length;
		int min;
		int temp;

		for (int i = 0; i < len; i++) {
			min=i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			temp = arr[i];
			arr[i]=arr[min];
			arr[min]=temp;
		}
		System.out.println("选择排序" + Arrays.toString(arr));
	}
}
