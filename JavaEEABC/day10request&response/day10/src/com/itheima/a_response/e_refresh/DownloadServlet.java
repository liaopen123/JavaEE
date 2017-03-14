package com.itheima.a_response.e_refresh;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String parameter = request.getParameter("name");
	ServletContext servletContext = getServletContext();
	String mimeType = servletContext.getMimeType(parameter);
	
	response.setContentType(mimeType);
	response.addHeader("content-disposition", "attachment;filename="+parameter);
	InputStream resourceAsStream = servletContext.getResourceAsStream("/"+parameter);
	ServletOutputStream outputStream = response.getOutputStream();
	int length = -1;
	byte[] b =new byte[1024];
	
	while((length =resourceAsStream.read(b))!=-1){
		outputStream.write(b,0,length);
	}
	
	outputStream.close();
	resourceAsStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
