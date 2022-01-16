package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {

	// import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// field
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	public BoardDao() {
	}

	// Connection
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}// getConnection

	// close
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// all list
	public List<BoardVo> getList() {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		getConnection();

		try {
			// sql문 준비, 바인딩, 실행
			String query = "";
			query += " select bd.no ";
			query += " title, ";
			query += " content, ";
			query += " hit, ";
			query += " to_char(reg_date, 'yyyy-mm-dd hh:mi:ss')regDate, ";
			query += " user_no, ";
			query += " id, ";
			query += " password, ";
			query += " name, ";
			query += " from board bd, users us ";
			query += " where bd.user_no = us.no ";
			query += " order by regDate desc ";

			// 쿼리문
			pstmt = conn.prepareStatement(query);

			// 바인딩 (x)

			// 실행 rs = result select
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regDate");
				int userNo = rs.getInt("userNo");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");

				// vo
				BoardVo boardVo = new BoardVo(no, title, content, hit, regDate, userNo, id, password, name);
				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return boardList;
	}

	// modify
	public void modify(BoardVo boardVo) {
		try {
			getConnection();

			String query = "";
			query += " update board ";
			query += " set title = ? ";
			query += " ,content = ? ";
			query += " where board.no = ? ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNo());

			// 실행
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// delete
	public void delete(int no, String password) {
		int count = 0;
		getConnection();

		try {
			// sql문 준비, 바인딩, 실행
			String query = "";
			query += " delete from board ";
			query += " where board.no = ? ";
			query += " and board.user_no =? ";

			// 문자열 -> 쿼리문
			pstmt = conn.prepareStatement(query);

			// 바인딩 (?)
			pstmt.setInt(1, no); // 1
			pstmt.setString(2, password); // 2

			count = pstmt.executeUpdate();

			System.out.println(count + "글이 삭제되었습니다 ");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}
}
