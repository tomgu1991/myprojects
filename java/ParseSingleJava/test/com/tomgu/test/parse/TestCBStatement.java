package com.tomgu.test.parse;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.EnhancedForStatement;

public class TestCBStatement {

	public static void main(String[] args) {
		TestCBStatement stmt = new TestCBStatement();
		assert stmt.str1.contains("11"):stmt.str2;
	}


	public String str1 = "str1";
	public String str2 = "str2";
	
	public TestCBStatement(){
		this(1);
	}

	public TestCBStatement(int i) {
		
	}
	public void enhancedForStatement(){
		List<String> list = new ArrayList();
		for( String s:list){
			System.out.println(s);
		}
	}
	
	public void ifStatement(int j){
		int i=j;
		if(i>0){
			System.out.println(i);
		}else if (i==0){
			System.out.println(i);
		}else
			System.out.println(-i);
	}
	public void swtichStatement(){
		int i=1;
		int result;
		switch (i) {
		case -1:
			result = 1;
			break;
		case 0:
			result = 0;
			break;
		case 1:
		case 2:
			result = 1;
			break;
		default:
			break;
		}
	}
}
