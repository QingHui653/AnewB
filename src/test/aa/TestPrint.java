package test.aa;

public class TestPrint {
	public static void main(String[] args) {
		for (int i = 1; i <= 15; i++) {
			if(i%15==0)
				System.out.println(i+"  15");
			else if (i%3==0) 
				System.out.println(i+"  3");
			else if(i%5==0)
				System.out.println(i+"  5");
		}
	}
}
