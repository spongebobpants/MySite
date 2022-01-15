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

@WebServlet("/guestbook")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("guestbookcontroller");
		// 컨트롤러 작동 확인

		String action = request.getParameter("action");
		// action = write
		if ("add".equals(action)) {
			System.out.println("guestbook = add");

			// 파라미터 -> vo
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			String regDate = request.getParameter("regDate");

			// vo
			// dao에 새 메모리 추가
			GuestbookDao guestbookDao = new GuestbookDao();
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			System.out.println(guestbookVo);
			// insert
			guestbookDao.insert(guestbookVo);

			// redirect
			WebUtil.redirect(request, response, "/MySite/guestbook");

		} else if ("deleteForm".equals(action)) {
			System.out.println("guestbook = deleteForm");
			// forward
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");

		} else if ("delete".equals(action)) {
			System.out.println("guestbook = delete");

			// Dao
			GuestbookDao guestbookDao = new GuestbookDao();
			// 형변환
			int no = Integer.parseInt(request.getParameter("no"));
			// deleteform
			String password = request.getParameter("password");
			guestbookDao.delete(no, password);

			// redirect
			WebUtil.redirect(request, response, "/MySite/guestbook");

		} else {
			//list
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();
			request.setAttribute("gList", guestbookList);
			
			//forward
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");

		}

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	} // doPost

}