package com.news.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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
			PreparedStatement preparedStatement = connection.prepareStatement("insert into news (id,user,news,lat,longi,area,title)"
					+ "values (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, CommonUtil.getPrimaryKey());
			preparedStatement.setString(2, message.getUser());
			preparedStatement.setString(3, message.getNews());
			preparedStatement.setFloat(4, message.getLat());
			preparedStatement.setFloat(5, message.getLongi());
			preparedStatement.setString(6, message.getAreaName());
			preparedStatement.setString(7, message.getTitle());
			
			int insertedRow = preparedStatement.executeUpdate();
			return "Success. Row count + "+insertedRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Faild to save message ";
		}
		
		
	}
	public static String getMessages(){
		Connection connection = CommonUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select id,user,news,area,title,createdon from news");
			ResultSet resultSet = preparedStatement.executeQuery();
			JSONArray array = new JSONArray();
			while(resultSet.next()){
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("id", resultSet.getString("id"));
				 jsonObject.put("user", resultSet.getString("user"));
				 jsonObject.put("news", resultSet.getString("news"));
				 jsonObject.put("area", resultSet.getString("area"));
				 jsonObject.put("title", resultSet.getString("title"));
				 jsonObject.put("createon", resultSet.getDate("createdon").toString());
				 array.add(jsonObject);
			}
			return array.toJSONString();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] ss){
		Message message = new Message();
		message.setUser("dea06e0d-9c7c-4662-a60b-4e8b7cf6a46c");
		message.setAreaName("SRM Mini Hall 2");
		message.setLat(12.56734637f);
		message.setLongi(34.76346737f);
		message.setNews("Hakathon event going on");
		message.setTitle("Hackathon");
		System.out.println(saveMessage(message));
	}
}
