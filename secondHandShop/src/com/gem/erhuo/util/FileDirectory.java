package com.gem.erhuo.util;

import java.io.IOException;
import java.util.Properties;

public class FileDirectory {
	public static String getFileSaveDirectory(){
		Properties p = new Properties();
		try {
			p.load(FileDirectory.class.getResourceAsStream("fd.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.getProperty("fileDirectory");
	} 
}
