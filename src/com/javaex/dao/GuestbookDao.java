package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {

	// import java.sql.*;

	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// field
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	public GuestbookDao() {

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
	public List<GuestbookVo> getList() {
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
		getConnection();

		try {
			// sql문 준비, 바인딩, 실행
			String query = "";
			query += " select	no ";
			query += " 			,name ";
			query += " 			,password ";
			query += " 			,content ";
			query += " 			,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate ";
			query += " from guestbook ";
			query += " order by regDate desc ";
			// 쿼리문
			pstmt = conn.prepareStatement(query);

			// 바인딩 (x)

			// 실행 rs = result select
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("regDate");

				// vo
				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, regDate);
				guestbookList.add(guestbookVo);
			} // while

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return guestbookList;
	}

	// insert
	public void insert(GuestbookVo guestbookVo) {
		int count = 0;
		getConnection();

		try {
			String query = "";
			query += " insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, '?', '?', '?', ?) ";

			// query
			pstmt = conn.prepareStatement(query);

			// binding
			pstmt.setInt(1, guestbookVo.getNo());
			pstmt.setString(2, guestbookVo.getName());
			pstmt.setString(3, guestbookVo.getPassword());
			pstmt.setString(4, guestbookVo.getContent());
			pstmt.setString(5, guestbookVo.getRegDate());

			// 실행
			count = pstmt.executeUpdate();

			// 4.
			System.out.println(count + "건이 등록되었습니다");

		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		close();

	}

	// delete
	public void delete(int no, String password) {
		int count = 0;
		getConnection();
		try {
			//sql문 준비, 바인딩, 실행
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ?";
			query += " and password = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no); //1.?
			pstmt.setString(2, password); //2.?
			
			count =pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다");
			
		}catch (SQLException e) {
			System.out.println("error" + e);
	}close();
	
	}
}
