package test.arith;

public class InsertSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 直接插入排序 
		 * 1.数组的第0位不能由数据
		 * 2.数组从第二个数据开始处理
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
	}
	

}
