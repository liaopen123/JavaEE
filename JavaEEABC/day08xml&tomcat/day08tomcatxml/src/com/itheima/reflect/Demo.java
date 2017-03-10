package com.itheima.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

import com.itheima.HelloMyServlet;

public class Demo {

	
	@Test
	public void f1() throws Exception{
		//1.获取class对象  3种方法
		Class clazz = Class.forName("com.itheima.HelloMyServlet");
		//Class clazz = HelloMyServlet.class;
		//对象.getClass():
//		HelloMyServlet hello = new HelloMyServlet();
//		Class clazz = hello.getClass();
	
		//获取对象：
		Object newInstance = clazz.newInstance();
		//获取方法：
		Method method = clazz.getMethod("add", null);
		
		method.invoke(newInstance, null);
		
		Method method1 =	clazz.getMethod("add", int.class,int.class);
		method1.invoke(newInstance, 1,4);
		
		
	}
}
