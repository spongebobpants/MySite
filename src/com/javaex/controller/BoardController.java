package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
	}
	// 생략 가능

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("/board");
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	
	//modifyForm
	if("read".equals(action)) {
		
	}else if("delete".equals(action)) {
		
	}else if("writeForm".equals(action)) {
		
	}else if("write".equals(action)) {
		
	}
	else if ("modifyForm".equals(action)){
		System.out.println("modifyForm");
		
		//forward
		WebUtil.forward(null, null, "/WEB-INF/views/board/modifyForm.jsp");
		
		//modify
	}else if("modify".equals(action)) {
		//getParameter
		int no = Integer.parseInt(request.getParameter("no"));
		
		
		}
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
