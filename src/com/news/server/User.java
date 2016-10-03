package com.news.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private String id;
	private String username;
	private String password;
	private boolean userstatus;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isUserstatus() {
		return userstatus;
	}
	public void setUserstatus(boolean userstatus) {
		this.userstatus = userstatus;
	}
	public String getId() {
		return id;
	}
	
	public static String saveUser(User user){
		Connection connection = CommonUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into user (id,username,password,userstatus) "
					+ "values (?,?,?,?)");
			preparedStatement.setString(1, CommonUtil.getPrimaryKey());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, 1);
			
			int insertedRow = preparedStatement.executeUpdate();
			return "Success. Row count + "+insertedRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Faild to save message ";
		}
	}
	
	public static void main (String[] rr){
		User user = new User();
		user.setUsername("arockiaraj1994@gmail.com");
		user.setPassword("123456");
		System.out.println(saveUser(user));
	}
	
	
	public static String login(String username,String password){
		Connection connection = CommonUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select id from user where username=? and password=? and userstatus=1");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			String id = "";
			while(resultSet.next()){
				 id = resultSet.getString("id");
				 break;
			}
			return id;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
