package com.news.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
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
			String messageData = Message.getMessages();
			response.getWriter().write(messageData);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Message message = new Message();
		JSONObject jsonObject = (JSONObject) request.getAttribute("messagedata");
		
		String insertRowsCount = Message.saveMessage(message);
		response.getWriter().write(insertRowsCount);
	}

}
