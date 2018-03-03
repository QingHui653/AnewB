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
    public void luoxuanArrayTest(){
//        luoxuanArray(4);
//        luoxuanArray(5);
//        luoxuanArray(6);

        luoxuanArray(4,4,true,false);
        System.out.println("------");
        luoxuanArray(4,4,false,false);
        System.out.println("------");
        luoxuanArray(4,4,true,true);
        System.out.println("------");
        luoxuanArray(4,4,false,true);
    }

    //只是 正螺旋 未考虑 反螺旋 和 向下 开始的螺旋
    public void luoxuanArray(int size){

        int[][] array= new int[size][size];

        int i = 0,j=0;

        int pos =0,len = size*size;

        int turnSize=size,turnedSize=size; //经过 几次 赋值 转向；

        int path=1;

        for (pos=0;pos<len;pos++) {

            array[i][j]=pos+1;

            turnedSize--;
            //判断 是否 需要转向
            if(turnedSize==0){
                // 向下 向上 时 需要 减少 次数
                if(path==1||path==3)
                    turnSize--;

                //转向 重新 设置 需要 几次
                turnedSize=turnSize;

                // 只有 4 个 方向
                path++;
                if(path>4)path=1;
            }

            switch (path){
                //向右 j--
                case 1:j++;break;
                //向下
                case 2:i++;break;
                //向左
                case 3:j--;break;
                //向上
                case 4:i--;break;
            }
        }

        printArray(array);
    }


    public void luoxuanArray(int row,int col,boolean isPos, boolean isTurnDown){

        int[][] array= new int[row][col];

        int i = 0,j=0;
        // 判断 正 反 向
        int pos =isPos?1:row*col,len =isPos?row*col:1;

        int turnSize=row,turnedSize=row; //经过 几次 赋值 转向；

        int path=1;
        // 判断 正 反 向
        for (;isPos?pos<=len:pos>=len;) {

            if(isTurnDown){
                array[j][i]=pos;
            }else {
                array[i][j]=pos;
            }

            turnedSize--;
            //判断 是否 需要转向
            if(turnedSize==0){
                // 向下 向上 时 需要 减少 次数
                if(path==1||path==3)
                    turnSize--;

                //转向 重新 设置 需要 几次
                turnedSize=turnSize;

                // 只有 4 个 方向
                path++;
                if(path>4)path=1;
            }

            switch (path){
                //向右 j--
                case 1:j++;break;
                //向下
                case 2:i++;break;
                //向左
                case 3:j--;break;
                //向上
                case 4:i--;break;
            }

            //判断 正反向 操作
            if(isPos){
                pos++;
            }else {
                pos--;
            }
        }

        printArray(array);
    }
}
