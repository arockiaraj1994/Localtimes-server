package com.news.server;

import java.sql.DriverManager;
import java.util.UUID;

import java.sql.Connection;

public class CommonUtil {
	
	public static String getPrimaryKey(){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		return uuid.toString();
	}
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3030/newsapp","root","january1994");
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	public static void main(String[] test){
		getPrimaryKey();
	}
}
