package com.news.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;


/**
 * Servlet implementation class MessageServer
 */
@WebServlet("/MessageServer")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String messageId = request.getParameter("messageId");
		if(messageId != null && !messageId.trim().isEmpty()){
			System.out.println(messageId);
			String singleMessageData  = Message.getSingleMessageById(messageId);
			response.getWriter().write(singleMessageData);
		}else {
			String lati = request.getParameter("lati");
			String longi = request.getParameter("longi");
			System.out.println("Lati :"+lati +"\nLongi :"+longi);
			String messageData = Message.getMessages(lati,longi);
			response.getWriter().write(messageData);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[32];
        int reader=0;
        while( reader >= 0 ) {
            reader = inputStream.read(buf);
            if( reader >= 0 ){
            	byteArrayOutputStream.write(buf, 0, reader);
            }
        }
        String postFeedData = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        
        JSONObject jsonObject = (JSONObject) JSONValue.parse(postFeedData);
		
        Message message = new Message();
        message.setAreaName(jsonObject.get("area").toString());
        message.setLat(Float.parseFloat(jsonObject.get("lat").toString()));
        message.setLongi(Float.parseFloat(jsonObject.get("longi").toString()));
        message.setNews(jsonObject.get("news").toString());
        message.setTitle(jsonObject.get("title").toString());
        message.setUser(jsonObject.get("user").toString());
        
		String insertRowsCount = Message.saveMessage(message);
		response.getWriter().write(insertRowsCount);
	}

}
