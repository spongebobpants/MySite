package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	public static void forward(HttpServletRequest request, 
			            HttpServletResponse response, String path) throws ServletException, IOException {
		System.out.println("forward");
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public static void redirect(HttpServletRequest request, 
                                HttpServletResponse response, String url) throws IOException {
		System.out.println("redirect");
		response.sendRedirect(url);
		
	}
	
}