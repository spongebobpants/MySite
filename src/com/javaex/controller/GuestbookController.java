package com.javaex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GuestbookController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getParameter("action");

		if ("write".equals(action)) {
			System.out.println("user > write");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			String regDate = request.getParameter("regDate");

			GuestbookVo guestbookVo = new GuestbookVo(name, password, content, regDate);

			System.out.println(guestbookVo);

			GuestbookDao guestbookDao = new GuestbookDao();

			guestbookDao.insert(guestbookVo);

			WebUtil.redirect(request, response, "/mysite/guest");

		} else if ("deleteForm".equals(action)) {
			System.out.println("user > deleteForm");

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");

		} else if ("delete".equals(action)) {
			System.out.println("guestbook > delete");

			GuestbookDao guestbookDao = new GuestbookDao();

			int ContentNo = Integer.parseInt(request.getParameter("no"));

			String password = request.getParameter("password");

			guestbookDao.delete(ContentNo, password);

			WebUtil.redirect(request, response, "/mysite/guest");

		} else {
			System.out.println("방명록 리스트");

			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();


			request.setAttribute("gList", guestbookList);

			WebUtil.redirect(request, response, "/mysite/guest");

		}

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	} // doPost

}