package newb.c.api.weather;

import org.junit.Test;

public class WeatherTest {
	
	@Test
	public void testQueryWeather(){
		weather w= new weather();
		String weatherJson = w.queryWeather("邵阳");
		System.out.println(weatherJson);
	}
}
