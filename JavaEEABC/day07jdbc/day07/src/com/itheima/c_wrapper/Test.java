package com.itheima.c_wrapper;

public class Test {
public static void main(String[] args){
	QQCar qq = new QQCar();
	
	PorscheCar porsche = new PorscheCar(qq);
	porsche.run();
	porsche.stop();
}
}
