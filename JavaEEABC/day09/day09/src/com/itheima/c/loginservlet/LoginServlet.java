package com.itheima.c.loginservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		resp.setContentType("text/html;charset=utf-8");
		System.out.println(username+password);
		 try {
			User user = new LoginService().getUser(username, password);
			
			if(null!=user){
			
			resp.getWriter().print("欢迎"+username+"回来");
			}else{
				resp.getWriter().print("对不起，账号密码错误");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print("网络错误，请稍后再试");
		}
	}

}
