package com.gem.erhuo.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException{
		Properties p = new Properties();
		p.load(DBConnection.class.getResourceAsStream("db.properties"));
		String driver = p.getProperty("driver");
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String pwd = p.getProperty("pwd");
		Class.forName(driver);
		return DriverManager.getConnection(url, user, pwd);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		System.out.println(getConnection());
	}

}
