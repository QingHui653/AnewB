package test.core.arith;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 * @author woshizbh
 *
 */

/**
 * 确定一个 枢轴;
 * 将大的 移到枢轴右边
 * 小的移到左边.
 * 重复
 */
public class QuickSort {

	@Test
	public void TestQuickSort(){
		int[] arr = { 5, 44, 38, 3, 47,};
		quickSort(arr);
		System.out.println("快速排序" + Arrays.toString(arr));
		int[] arr2 = { 5, 44, 38, 3, 47,};
		quicksort2(arr2);
		System.out.println("快速排序2" + Arrays.toString(arr2));

	}

	public static void quickSort(int[] arr){
		qsort(arr, 0, arr.length-1);
	}
	private static void qsort(int[] arr, int low, int high){
		if (low < high){
			int pivot=partition(arr, low, high);        //将数组分为两部分
			qsort(arr, low, pivot-1);                   //递归排序左子数组
			qsort(arr, pivot+1, high);                  //递归排序右子数组
		}
	}
	private static int partition(int[] arr, int low, int high){
		int pivot = arr[low];     //枢轴记录
		while (low<high){
			while (low<high && arr[high]>=pivot) { // 判断是否 大于枢轴 记录.大于 则跳过.小于才交换
				--high;
			}
			arr[low]=arr[high];             //交换比枢轴小的记录到左端
			while (low<high && arr[low]<=pivot) { // 判断是否 小于枢轴 记录.小于 则跳过.小于才交换
				++low;
			}
			arr[high] = arr[low];           //交换比枢轴小的记录到右端
		}
		//扫描完成，枢轴到位
		arr[low] = pivot;
		//返回的是枢轴的位置
		return low;
	}


	public void quicksort2(int[] arr){
		qsort2(arr,0,arr.length-1); //-1 很重要
	}

	public void qsort2(int[] arr,int low ,int high){
		if(low<high){
			int pivot = partition2(arr,low,high);
			qsort2(arr,0,pivot-1); //-1
			qsort2(arr,pivot+1,high); //+1

		}
	}

	public int partition2(int[] arr,int low ,int high){
		int pivot = arr[low];
		while (low<high){
			while (low<high && arr[high]>=pivot)high--;
			arr[low]=arr[high];
			while (low<high && arr[low]<=pivot) low++;
			arr[high]=arr[low];
		}
		arr[low]=pivot;
		return low;
	};
}
