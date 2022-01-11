package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

@WebServlet("/user")

public class UserController extends HttpServlet {

//기본 생성자	
	public UserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/user");
		String action = request.getParameter("action");
		// joinForm
		if ("joinForm".equals(action)) {
			System.out.println("user-joinForm");
			// http://localhost:8088/mysite/user?action=joinForm

			// forward
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");

			// join
		} else if ("join".equals(action)) {
			System.out.println("user-join");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");

			UserVo userVo = new UserVo(id, password, name, gender);

			// userVo import check
			System.out.println(userVo);
			
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
		} else if ("joinOk".equals(action)) {
			System.out.println("user > joinOk 시작");

		
		} else if ("loginForm".equals(action)) {
			System.out.println("main > loginForm 시작");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");

		} else if ("addList".equals(action)) {
			System.out.println("user > addList 시작");

			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();

			request.setAttribute("gList", guestbookList);
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");

		} else {
			System.out.println("파라미터가 잘못 되었습니다.");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
