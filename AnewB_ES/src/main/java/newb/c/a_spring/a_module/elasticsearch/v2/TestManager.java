package newb.c.a_spring.a_module.elasticsearch.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import newb.c.a_spring.backend.elasticsearch.model.MovieDTO;
import org.junit.Test;


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
	
	@Test
	public void testMovie() {
		MovieDTO  movie = new MovieDTO();
			movie.setMovieName("ailce");
			movie.setMovieLink("www.ailce.com");
		List<Object> list =new ArrayList<>();
		list.add(movie);
		
		insertManager.save("wow", "esmovie", "", list);
	}
	
	@Test
	public void findByid() {
		MovieDTO movie = queryManage.findById("wow","esmovie","259");
		System.out.println("-- "+movie.toString());
	}
}
