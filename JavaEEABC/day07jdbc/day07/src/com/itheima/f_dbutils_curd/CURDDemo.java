package com.itheima.f_dbutils_curd;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.itheima.util.DataSourceUtils;

public class CURDDemo {
	@Test
	public void insert() throws SQLException{
		//1.创建queryRunner类 
			QueryRunner qr =  new QueryRunner(DataSourceUtils.getDataSource());
		//2.编写sql  
		String sql = "insert into category values(?,?)";
		//3.执行sql
		qr.update(sql, "12","jolin");
	}
	
	@Test
	public void update() throws SQLException{
		//1.创建queryRunner类 
		QueryRunner qr =  new QueryRunner(DataSourceUtils.getDataSource());
	//2.编写sql  
	String sql = "update category set cname=? where cid = ?";
	//3.执行sql
	qr.update(sql, "liaopenghui","12");
	}
}
