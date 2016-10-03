package com.news.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class Message {
	private String id;
	private String user;
	private String news;
	private String title;
	private float lat;
	private float longi;
	private String areaName;
	private Date createon;
	
	
	public Date getCreateon() {
		return createon;
	}
	public void setCreateon(Date createon) {
		this.createon = createon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLongi() {
		return longi;
	}
	public void setLongi(float longi) {
		this.longi = longi;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public static String saveMessage(Message message){
		Connection connection = CommonUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into news values "
					+ "(id=?,user=?,news=?,lat=?,longi=?,area=?,createdon=?,title=?)");
			preparedStatement.setString(1, CommonUtil.getPrimaryKey());
			preparedStatement.setString(2, message.getUser());
			preparedStatement.setString(3, message.getNews());
			preparedStatement.setFloat(4, message.getLat());
			preparedStatement.setFloat(5, message.getLongi());
			preparedStatement.setString(6, message.getAreaName());
			preparedStatement.setDate(7, (java.sql.Date) new Date());
			preparedStatement.setString(8, message.getTitle());
			
			int insertedRow = preparedStatement.executeUpdate();
			return "Success. Row count + "+insertedRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Faild to save message ";
		}
	}
	
}
