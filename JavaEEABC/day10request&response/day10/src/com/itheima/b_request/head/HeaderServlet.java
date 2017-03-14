package com.itheima.b_request.head;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderServlet
 */
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//获取浏览器内核
				String agent = request.getHeader("user-agent");
				System.out.println(agent);
				
				//获取referer
				String referer = request.getHeader("referer");
				if(referer==null){
					System.out.println("直接在地址栏上输入的");
				}else if(referer.contains("localhost")){
					System.out.println("我自己点的..");
				}else if(referer.contains("192.168.")){
					System.out.println("哥们儿姐们儿点的");
				}else{
					System.out.println("盗链者可耻");
				}
				String name = request.getParameter("name");//获取一个值
				System.out.println("name:"+name);
				String[] parameterValues = request.getParameterValues("hobby");//通过一个key获取多个值
				System.out.println("hobby:"+Arrays.toString(parameterValues));
				Map<String, String[]> map = request.getParameterMap();// 获取所有的参数名称和值
				for(String key:map.keySet()){
					System.out.println(key+"::"+Arrays.toString(map.get(key)));
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
