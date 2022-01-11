package com.javaex.dao;

import com.javaex.vo.*;

public class TestDao {
public static void main(String []args) {

	UserVo userVo = new UserVo("ccc", "1234", "김연아", "female");
	UserDao userDao = new UserDao();
	userDao.insert(userVo);
	
	//run as -> java application
	}
}
		
		//connction

