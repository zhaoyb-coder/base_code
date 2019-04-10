package com.test;

 
public class QuartzTest {
	private String hello;

	/**
	 * @return hello
	 */
	public String getHello() {
		return hello;
	}

	/**
	 * @param hello 
	 */
	public void setHello(String hello) {
		this.hello = hello;
	}
	
	
	public void testStart() {
		System.out.println("====>>" + hello);
	}
}
