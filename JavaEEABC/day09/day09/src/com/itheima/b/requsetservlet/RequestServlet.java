package com.itheima.b.requsetservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��������
		String value = req.getParameter("username");
		System.out.println(value);
		//http://localhost:8080/day09/Request?username=jay
		
		//��д����
		
		//��ֹ����
		resp.setContentType("text/html;charset=utf-8");
		
		resp.getWriter().print("�õ���name"+value);
	}

}
