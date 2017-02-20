package com.itheima.d_dbcp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import com.itheima.util.JdbcUtils;

public class DBCPDemo {
//		@Test
//	public void f1() throws Exception{
//		BasicDataSource bds = new BasicDataSource();
//		bds.setDriverClassName("com.mysql.jdbc.Driver");
//		bds.setUrl("jdbc:mysql://localhost:3306/day07");
//		bds.setUsername("liaopenghui");
//		bds.setPassword("Liaopen123_");
//		
//		Connection connection = bds.getConnection();
//		String sql = "insert into category values(?,?)";
//		PreparedStatement prepareStatement = connection.prepareStatement(sql);
//		prepareStatement.setString(1, "c11");
//		prepareStatement.setString(2, "andy");
//		
//		prepareStatement.executeUpdate();
//		
//		JdbcUtils.colseStream(null, prepareStatement, connection);
//		
//		
//	}
			@Test
		public void f2() throws Exception{
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/dbcp.properties"));
			//设置
			//prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
			
			//创建连接池
			DataSource ds = new BasicDataSourceFactory().createDataSource(prop);
			Connection connection = ds.getConnection();
			String sql = "insert into category values(?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, "11");
			prepareStatement.setString(2, "andy");
			
			prepareStatement.executeUpdate();
			
			JdbcUtils.colseStream(null, prepareStatement, connection);
			
		}
}
