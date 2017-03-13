package com.itheima.c.loginservlet;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


public class UserDao {

	public User getUserByUsernameAndPwd(String username, String password) throws Exception {
					QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
					
					String sql = "select count(*) from user";
					//执行sql
//		User user = queryRunner.query(sql, new BeanHandler<>(User.class), username,password);
					Object query = queryRunner.query(sql, new ScalarHandler());
					System.out.println(query);
		return null;
	}
		
}
