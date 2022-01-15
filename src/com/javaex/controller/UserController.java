package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")

public class UserController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/user");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		// joinForm
		if ("joinForm".equals(action)) {
			System.out.println("user-joinForm");
			// http://localhost:8088/mysite/user?action=joinForm

			// forward
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");

			// join
		} else if ("join".equals(action)) {
			//파라미터값 가져오기
			System.out.println("user-join");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			//파라미터-> vo로 만들기
			UserVo userVo = new UserVo(id, password, name, gender);
			// userVo import check
			//System.out.println(userVo);
			
			//userDao의 insert()로 저장하기(회원가입하기)
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
			//login
		} else if ("loginForm".equals(action)) {
			System.out.println("user-loginForm");
			//forward
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");

		}else if("login".equals(action)) {
			
			System.out.println("user-login");
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id, password);
			//System.out.println(userVo);
			
			if(authVo == null) {//authVo -> x 로그인 불가
				System.out.println("Login failed");
				WebUtil.redirect(request, response, "/MySite/user?action=loginForm");				
			}else {//login success
				System.out.println("Login success");
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authVo);
				WebUtil.redirect(request, response, "/MySite/main");
			}
		}else if("logout".equals(action)) {
			System.out.println("user-logout");
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			WebUtil.redirect(request, response, "/MySite/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
