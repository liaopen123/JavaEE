package com.itheima.c_wrapper;

public class PorscheCar implements Car {
	public Car car;
	public PorscheCar(Car car) {
		super();
		this.car = car;
	}

	@Override
	public void run() {
System.out.println("加上一个马达，跑的飞起");		

	}

	@Override
	public void stop() {
car.stop();
	}

}
