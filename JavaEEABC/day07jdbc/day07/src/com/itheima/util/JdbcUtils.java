package com.itheima.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {
  
	public static Connection getConnection(String className,String jdbcHost,String user,String pwd) throws Exception{
		//注册驱动
				Class.forName(className);
				//获取连接
				Connection conn = DriverManager.getConnection(jdbcHost,user,pwd);
				
				return conn;
	}
	
	
	public static void colseStream(ResultSet rs,PreparedStatement st,Connection conn) throws Exception{
		rs.close();
		st.close();
		conn.close();
	}
}
