package test.base;

import java.util.Arrays;

import com.sun.tools.doclets.internal.toolkit.Configuration;

public class arrays {

	public static void main(String[] args) {
		int[] intArray = new int[] {1, 2, 3, 4, 5};
		System.out.println(Arrays.toString(intArray));
		//输出: [1, 2, 3, 4, 5]

		String[] strArray = new String[] {"John", "Mary", "Bob"};
		System.out.println(Arrays.deepToString(strArray));
		//输出: [John, Mary, Bob]
		
		
		
		String[][] b = new String[3][4];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                b[i][j] = "A" + j;
            }
        } 
        System.out.println(Arrays.toString(b));
        //输出[[Ljava.lang.String;@55e6cb2a, [Ljava.lang.String;@23245e75, [Ljava.lang.String;@28b56559]
        System.out.println(Arrays.deepToString(b));  //适合打印多维数组
        //输出[[A0, A1, A2, A3], [A0, A1, A2, A3], [A0, A1, A2, A3]]
        
	}

}
