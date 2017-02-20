package com.itheima.b_datasourc.b_my;

import java.sql.Connection;
import java.util.LinkedList;

import com.itheima.util.JdbcUtils;

public class MyDataSource {
	public static LinkedList<Connection> pools;
  static{
	 pools =new LinkedList();
	  for(int i =0;i<3;i++){
		 try {
			Connection conn =  JdbcUtils.getConnection("", "","","");
			pools.addLast(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
  }
  
  
  public static Connection getConnection(){
	  if(pools.isEmpty()){
		  for(int i =0;i<3;i++){
				 try {
					Connection conn =  JdbcUtils.getConnection("", "","","");
					pools.addLast(conn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
	  }
	  
	  return pools.removeLast();
  }
  
  
  
  public static void addBack(Connection conn){
	  pools.addLast(conn);
  }
}
