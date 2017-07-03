package test.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class config {

	public static void main(String[] args) {
		Configurations configs = new Configurations();
		try {
			String a = "aa";
			a.toUpperCase();
//			PropertiesConfiguration config = configs.properties(new File("log4j.properties"));
//			String url = config.getString("log4j.rootLogger");
			PropertiesConfiguration config = configs.properties(new File("src/main/resources/config.properties"));
			
//			String res = ResourceBundle.getBundle("src/main/resources/config.properties").getString("jdbc.url");
			String url = config.getString("jdbc.url");
			
//			System.out.println("res--"+res);
			System.out.println("url--"+url);

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		
	}
}
