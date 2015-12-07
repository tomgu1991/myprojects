package com.tomgu.test;

import com.tomgu.util.FileReaderUtil;

public class TestGetPositionByLineNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int lineNumber = 227;
		String filePath = "./source/bug.java";
		int result = FileReaderUtil.getPositionByLineNumber(filePath, lineNumber);
		System.out.println("line " + lineNumber +" position is : " + result);
	
	
	
	}

}
