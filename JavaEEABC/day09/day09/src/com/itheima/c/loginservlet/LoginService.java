package com.itheima.c.loginservlet;

public class LoginService {

	
	
	public User getUser(String username,String password) throws Exception{
		
	User user = new UserDao().getUserByUsernameAndPwd(username,password);
		return user;
	}
}
