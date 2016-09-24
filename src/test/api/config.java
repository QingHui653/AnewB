package test.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class config {

	public static void main(String[] args) {
		Configurations configs = new Configurations();
		try {
			PropertiesConfiguration config = configs.properties(new File("config.properties"));
			
			String url = config.getString("cs");
			
			System.out.println("url--"+url);

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		
	}
}
