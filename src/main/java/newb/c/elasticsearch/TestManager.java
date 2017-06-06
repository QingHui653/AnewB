package newb.c.elasticsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import newb.c.backend.model.basemodel.Movie;

public class TestManager {
	
	private InsertManager insertManager =new InsertManager();
	
	private QueryManage queryManage =new QueryManage();
	
//	@Test
	public void testSave() {
		Map<String,Object> map =new HashMap<>();
			map.put("author", "qh");
			map.put("question", "1+1=?");
			map.put("answer", "2");
	
		List<Object> list =new ArrayList<>();
		list.add(map);
		
		insertManager.save("hi", "hello", "", list);
	}
	
//	@Test
	public void testMovie() {
		Movie  movie = new Movie();
			movie.setMovieName("ailce");
			movie.setMovieLink("www.ailce.com");
		List<Object> list =new ArrayList<>();
		list.add(movie);
		
		insertManager.save("wow", "movie", "", list);
	}
	
	@Test
	public void findByid() {
		Movie movie = queryManage.findById("wow","movie","AVx7mLUpZE3a5IVtpb2G");
		System.out.println("-- "+movie.toString());
	}
	
}
