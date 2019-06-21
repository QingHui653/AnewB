package test.core.java1_8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class stream {

	public static void main(String[] args) {
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
//		Stream.iterate(1, item -> item + 1).forEach(System.out::println);
		
		
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
			       "Stanislas Wawrinka",  
			       "David Ferrer","Roger Federer",  
			       "Andy Murray","Tomas Berdych",  
			       "Juan Martin Del Potro"};  
			List<String> players =  Arrays.asList(atp);
			players.forEach((player) -> System.out.print(player + "; "));
			
			int[] inp={5,3,6}; 		
			List<int[]> i = Arrays.asList(inp);
			i.forEach((l)->System.out.println(Arrays.toString(l)));
	}
	
	
}
