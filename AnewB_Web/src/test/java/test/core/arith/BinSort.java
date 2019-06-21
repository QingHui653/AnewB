package test.core.arith;

import org.junit.Test;

/**
 * @Auther:b
 * @Date: 2018/8/15
 * @Deseription 二分法
 */
public class BinSort {

    @Test
    public void TestBinSearch(){
        int[] a={2,3,5,7,9,13,18};
        int key= binSearch(a,13);
        System.out.println(key);

        int key2 = binSearch(a,0,a.length-1,9);
        System.out.println(key2);

    }

    /**
     * 二分查找普通实现。
     * @param srcArray 有序数组
     * @param key 查找元素
     * @return  不存在返回-1
     */
    public int binSearch(int srcArray[], int key) {
        int mid;
        int start = 0;
        int end = srcArray.length - 1;
        while (start <= end) {
            mid = (end - start) / 2 + start;
            if (key < srcArray[mid]) {
                end = mid - 1;
            } else if (key > srcArray[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 二分查找递归实现。
     * @param srcArray  有序数组
     * @param start 数组低地址下标
     * @param end   数组高地址下标
     * @param key  查找元素
     * @return 查找元素不存在返回-1
     */
    public int binSearch(int srcArray[], int start, int end, int key) {
        int mid = (end - start) / 2 + start;
        if (srcArray[mid] == key) {
            return mid;
        }
        if (start >= end) {
            return -1;
        } else if (key > srcArray[mid]) {
            return binSearch(srcArray, mid + 1, end, key);
        } else if (key < srcArray[mid]) {
            return binSearch(srcArray, start, mid - 1, key);
        }
        return -1;
    }


    public int binSearch2(int[] arrays ,int key){
        int mid, start = 0,end = arrays.length-1;
        while (start<end){
            mid = (end-start)/2+start;
            if(key<arrays[mid]){
                end = mid-1;
            } else if(key>arrays[mid]){
                start = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int binSearch2(int[] arrays ,int start,int end,int key){
        int mid = (end-start)/2+start;
        if(arrays[mid]==key) return mid;

        if(start>=end){
            return -1;
        }else if(key>arrays[mid]){
            return binSearch2(arrays,mid+1,end,key);
        }else if(key<arrays[mid]){
            return binSearch2(arrays,start,end-1,key);
        }
        return -1;
    }
}
