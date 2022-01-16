package com.javaex.vo;

public class BoardVo {

	// field
	private int no; //board.no (content)
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int userNo; //board.user_no
	private String id;
	private String password;
	private String userName;

	// constructor
	public BoardVo() {
	}

	public BoardVo(int no, String title, String content, int hit, String regDate, int userNo, String id,
			String password, String userName) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.id = id;
		this.password = password;
		this.userName = userName;
	}
	
	public BoardVo(int no, String password) {
		this.no = no;
		this.password = password;
	}

	public BoardVo(int no, String title, String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}
	
	// g.s

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// method

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", id=" + id + ", password=" + password + ", userName=" + userName
				+ "]";
	}

}
