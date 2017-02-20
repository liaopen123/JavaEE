package com.itheima.e_c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.itheima.util.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Demo {
		@Test
	public void f1() throws Exception{
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/day07?useUnicode=true&characterEncoding=utf-8&useSSL=false");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("Liaopen123_");
		Connection connection = comboPooledDataSource.getConnection();
		String sql = "insert into category values(?,?)";
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "16");
		prepareStatement.setString(2, "jjj");
		
		prepareStatement.executeUpdate();
		
		JdbcUtils.colseStream(null, prepareStatement, connection);
		
	}
		
		public void f2(){
			
		}
}
