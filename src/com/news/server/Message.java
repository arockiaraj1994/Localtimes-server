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
	public static String getMessages(String latit,String longi){
		Connection connection = CommonUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select id,user,news,area,title,createdon,distance from "
					+ "(SELECT *,3956 * 2 * ASIN(SQRT( POWER(SIN((? - abs(dest.lat)) * pi()/180 / 2),2) + COS(? * pi()/180 ) * COS( abs(dest.lat) * pi()/180) * "
					+ "POWER(SIN((? - abs(dest.longi)) * pi()/180 / 2), 2) )) as distance FROM news as dest) as fulldata where distance <= 1.0");
			preparedStatement.setFloat(1, Float.parseFloat(latit));
			preparedStatement.setFloat(2, Float.parseFloat(latit));
			preparedStatement.setFloat(3, Float.parseFloat(longi));
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
				 jsonObject.put("distance", resultSet.getString("distance").substring(0, 4));
				 array.add(jsonObject);
			}
			return array.toJSONString();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getSingleMessageById(String messageId) {
		Connection connection = CommonUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select id,user,news,area,title,createdon from news where id=?");
			preparedStatement.setString(1, messageId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("id", resultSet.getString("id"));
				 jsonObject.put("user", resultSet.getString("user"));
				 jsonObject.put("news", resultSet.getString("news"));
				 jsonObject.put("area", resultSet.getString("area"));
				 jsonObject.put("title", resultSet.getString("title"));
				 jsonObject.put("createon", resultSet.getDate("createdon").toString());
				 return jsonObject.toString();
			}
			return "";
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
