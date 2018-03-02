package test.core.javacase;

import org.junit.Test;

import java.util.Arrays;

/**
 * 关于 螺旋数组 与反螺旋 数组 的 处理
 */
public class SpiralArrayTest {
    public static void main(String[] args) {
        printArray(getSpiralArray(4,4,false));
        System.out.println("**************");
        printArray(getSpiralArray(5,5,false));
//        printArray(getSpiralArray(5,5,true));
    }
    //返回螺旋数组
    public static int [][] getSpiralArray(int m,int n,boolean reverse){
        //定义一个长度为m*n的数组，并按顺序初始化
        int [] numArray = new int[m*n];
        for(int i = 0 ; i < numArray.length ; i++){
            if(!reverse){
                numArray[i] = (i+1);
            }else{
                numArray[i] = (numArray.length-i);
            }
        }
        //初始化数组下标
        int foot = 0;

        //声明螺旋数组
        int[][] array = new int[m][n];
        //计算"层数"，以m和n中较小的数为准
        int layer = m<n?m:n;
        //判断 有 几层 需要循环几圈
        layer = (layer%2==1)?(layer / 2 + 1):(layer / 2);

        // 从外层到里层循环
        for (int i = 0; i < layer; i++) {
            //从左到右

            // 当为 正 螺旋时，第一层 不需要 变动
            for (int j = i; j < n - i; j++) {
                array[i][j] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
            // 从上到下
            for (int j = i + 1;  j < m - i; j++) {
                array[j][n - i - 1] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
            // 从右到左
            for (int j = n - i - 2; j >= i; j--) {
                array[m - i - 1][j] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
            // 从下到上
            for (int j = m - i - 2; j > i; j--) {
                array[j][i] = numArray[foot++];
                if(foot>=m*n){
                    return array;
                }
            }
        }
        return array;
    }
    //打印二维数组
    public static void printArray(int [][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j]<10){
                    System.out.print("0");
                }
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void luoxuanArray(){
        int size =5;
        int[][] array= new int[5][5];
        int i = 0,j=0,pos =0,len = size*size;
        int turnSize=size,turnedSize=size; //经过 几次 赋值 转向；
        int path=1;

        for (pos=0 ; pos<len; pos++) {
            printArray(array);
            System.out.println("------------");
            array[i][j]=pos+1;
            turnedSize--;
            //判断 是否 需要转向
            if(turnedSize==0){
                if(path==1&& path==4){
                    turnSize--;
                    turnedSize=turnSize;
                }
                path++;
                if(path>4){
                    path=1;
                }
            }

            switch (path){
                case 1:j++;break;
                case 2:i++;break;
                case 3:j--;break;
                case 4:i++;break;
            }
        }

        printArray(array);
    }
}
