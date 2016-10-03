package com.news.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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
			PreparedStatement preparedStatement = connection.prepareStatement("insert into user values "
					+ "(id=?,username=?,password=?,userstatus=?)");
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
	public static String login(String username,String password){
		
	}
}
