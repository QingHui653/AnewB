package test.arith;

public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double[] sorted = {0.0,25.2,332.2,63.8,63.5,34.3};
		int sortedLen =sorted.length;
		for(int j=2;j<sortedLen;j++){
			if(sorted[j]<sorted[j-1]){
				sorted[0] = sorted[j];
				sorted[j] = sorted[j-1];
				int insertPos=0;
				for (int k = j-2; k >=0 ; k--) {
					if(sorted[k]>sorted[0]){
						sorted[k+1]=sorted[k];
					}else {
						insertPos=k+1;
						break;
					}
				}
				sorted[insertPos]=sorted[0];
			}
		}
		
		for (Double after : sorted) {
			System.out.println("  "+after.toString());
		}
	}

}
