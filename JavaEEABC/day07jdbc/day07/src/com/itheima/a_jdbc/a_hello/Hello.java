package com.itheima.a_jdbc.a_hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.itheima.util.JdbcUtils;

public class Hello{
	@Test
   public void h1(){
	   System.out.print("hello");
   }
	@Test
	public void h2() throws Exception{
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day07","root","123");
		//编写sql
		String sql = "select * from category";
		//创建语句执行者
		PreparedStatement st = conn.prepareStatement(sql);
		
		//设置参数
		
		
		//执行sql
		ResultSet rs = st.executeQuery();
		
		//处理结果   
		while(rs.next()){
				System.out.print(rs.getString("cid")+":::"+rs.getString("cname"));
		}
		
		//释放资源  先打开的后关闭
		rs.close();
		st.close();
		conn.close();
		
	
	}
	
	@Test
	public void h3() throws Exception {
		Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/day07","root","123");
		//编写sql
				String sql = "insert into category values(?,?)";
				//创建语句执行者
				PreparedStatement st = conn.prepareStatement(sql);
				
				//设置参数
				st.setString(1, "c005");
				st.setString(2, "outdoor");
				//执行sql
				int executeUpdate = st.executeUpdate();///返回值为影响的行数
				
			
				//JdbcUtils.colseStream(rs, st, conn);
		
	}
	
	
	
}
