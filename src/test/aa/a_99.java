package test.aa;

public class a_99 {

	public static void main(String[] args) {

		new a_99().nn();

	}
	
	
	public void nn(){
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <=i; j++) {
				System.out.print(""+i+"*"+j+"="+i*j+"  ");
			}
			System.out.println("");
		}
	}
}
