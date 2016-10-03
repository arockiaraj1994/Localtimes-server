package com.news.server;

import java.util.UUID;

public class CommonUtil {
	
	public static String getPrimaryKey(){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		return uuid.toString();
	}
	public static void main(String[] test){
		getPrimaryKey();
	}
}
