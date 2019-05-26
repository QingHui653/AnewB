package test.core.arith;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 * @author woshizbh
 *
 */

/**
 * 从第一个元素开始，该元素可以认为已经被排序
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 将新元素插入到该位置后
 * 重复步骤2~5
 *
 * 如果比较操作的代价比交换操作大的话，可以采用二分查找法来减少比较操作的数目。该算法可以认为是插入排序的一个变种，称为二分查找插入排序。
 */
public class InsertSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 直接插入排序 
		 * 1.数组的第0位不能有数据     // 默认第一个 元素已经排序.
		 * 2.数组从第二个数据开始处理  //
		 * (如果该数据比前一位要小，说明要前移)
		 * (先将该数据备份到第0位)
		 * (将该数据前面那个数据后移)
		 * (然后在往前搜索，找到插入位置)
		 * (将第0位置插入对应位置)
		 */
		Double[] sorted = {0.0,332.2,25.2,73.8,63.5,44.3};
		//方法:在数组第0位加一0.0
		/*Double[] three= new Double[sorted.length+1];
		three[0]=0.0;
		int i=1;
		for (Double double1 : sorted) {
			three[i]=double1;
			i++;
		}*/
		int sortedLen =sorted.length;
		for(int j=2;j<sortedLen;j++){
			//从第2位开始比较,与前一位比较,如果比前位小,进入下一步
			if(sorted[j]<sorted[j-1]){
				System.out.println("1位数据 -- "+sorted[0]);
				System.out.println("第j--"+(j+1)+"位数据 -- "+sorted[j]);
				System.out.println("第j-1--"+(j)+"位数据 -- "+sorted[j-1]);
				sorted[0] = sorted[j];
				sorted[j] = sorted[j-1];
				for (Double after : sorted) {
					System.out.println("插入0位时 ~~ "+after.toString());
				}
				
				int insertPos=0;
				
				for (int k = j-2; k >=0 ; k--) {
					if(sorted[k]>sorted[0]){
						System.out.println("第k--"+k+"位数据 -- "+sorted[k]);
						System.out.println("第k+1--"+k+1+"位数据 -- "+sorted[k+1]);
						sorted[k+1]=sorted[k];
						
						for (Double after : sorted) {
							System.out.println("向前搜索 ** "+after.toString());
						}
						
					}else {
						insertPos=k+1;
						break;
					}
				}
				sorted[insertPos]=sorted[0];
				
				for (Double after : sorted) {
					System.out.println("将0位插入 ** "+after.toString());
				}
			}
		}
		
		/*for (Double after : sorted) {
			System.out.println("  "+after.toString());
		}*/
		
		for (int i = 1; i < sorted.length; i++) {
			System.out.println("  "+sorted[i]);
		}
		System.out.println("------------------------------插入排序");
		int[] sort = {0,332,25,73,63,44};
		int[] arr = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		InsertSort ins = new InsertSort();
		
		int[] c= ins.insertionSort(arr);
		int[] c2= ins.insertionSort(sort);
		
		System.out.println("--------插入排序"+Arrays.toString(c));
		System.out.println("--------插入排序"+Arrays.toString(c2));
	}
	
	public int[] insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
	}

	public void insertSort2(int[] arrays){
		for (int i = 1; i < arrays.length; i++) {
			int key = arrays[i];
			int j =i-1;
			while (j>=0 && arrays[j] >key){
				arrays[j+1]=arrays[j];
				j--;
			}
			arrays[j+1]=key;
		}
	}

	@Test
	public void TestInsertSort(){
		int[] arr = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));
		int[] arr2 = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
		insertSort2(arr2);
		System.out.println(Arrays.toString(arr2));
	}

}
