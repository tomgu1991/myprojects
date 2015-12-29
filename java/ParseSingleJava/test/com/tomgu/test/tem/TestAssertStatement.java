package com.tomgu.test.tem;

public class TestAssertStatement {

	public static void main(String[] args) {
		String result = "test assert statement";
		//add -ea to VM arguments
		assert result.contains("assert1"):"message";
		System.out.println("finish");
	}

}
