package com.itheima;

public class HelloMyServlet {

	public void add(){
		System.out.println("无参");
	}
	
	public void add(int i,int j){
		System.out.println("有参"+i+j);
	}
	
	public int add(int i){
		System.out.println("有参"+i);
		return i;
	}
}
