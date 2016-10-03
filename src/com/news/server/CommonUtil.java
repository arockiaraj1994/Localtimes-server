package com.news.server;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			/*Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from newsapp");
			while(resultSet.next()){
				
			}*/
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static void insertData(){
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into news values (?,?,?,?,?,?)");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] test){
		insertData();
	}
}
