package com.gem.erhuo.util;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class Url {
	
	public static String getHeadUrl(){
		Properties prop = new Properties();
		try {
			prop.load(Url.class.getResourceAsStream("url.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty("url");
	}

}
