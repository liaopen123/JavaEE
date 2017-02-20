package com.itheima.c_wrapper;

public class QQCar implements Car {

	@Override
	public void run() {
		System.out.println("垃圾车跑得慢");
	}

	@Override
	public void stop() {
		System.out.println("垃圾车没刹车");
	}

}
