package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
	}
	// 생략 가능

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/board");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// modifyForm
		if ("read".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVo boardVo = new BoardDao().read(no);
			request.setAttribute("boardVo", boardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");

		} else if ("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			int userNo = Integer.parseInt(request.getParameter("userNo"));

			BoardDao boardDao = new BoardDao();
			boardDao.delete(no, userNo);

			WebUtil.redirect(request, response, "/MySite/board");

		} else if ("writeForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");

		} else if ("write".equals(action)) {
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String id = request.getParameter("id");

			BoardVo boardVo = new BoardVo(userNo, title, content);

			BoardDao boardDao = new BoardDao();

			boardDao.insert(boardVo);

			WebUtil.redirect(request, response, "/MySite/board");

		} else if ("modifyForm".equals(action)) {
			System.out.println("modifyForm");

			// forward
			WebUtil.forward(null, null, "/WEB-INF/views/board/modifyForm.jsp");

			// modify
		} else if ("modify".equals(action)) {
			// getParameter
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			int boardNo = Integer.parseInt(request.getParameter("no"));

			BoardVo boardVo = new BoardVo(boardNo, title, content);

			BoardDao boardDao = new BoardDao();

			boardDao.modify(boardVo);

			WebUtil.redirect(request, response, "/MySite/board?action=read&no=" + boardNo);

		}
	}// doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
