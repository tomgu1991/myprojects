package com.tomgu.test.parse;

import java.io.File;

import com.tomgu.parse.JavaParser;
import com.tomgu.util.FileReaderUtil;

public class TestParseJavaFile {

	public static void main(String[] args) {
		int lineNumber = 227;
		String filePath = "./source/bug.java";
		File file = new File(filePath);
		System.out.println(filePath + " exist? "+file.exists());
		String codeStr = "";
		//codeStr = FileReaderUtil.readFileToString(filePath);
		//System.out.println(codeStr);
		codeStr = FileReaderUtil.readFileToStringByChar(filePath);
//		System.out.println(codeStr);
		JavaParser parser = new JavaParser();
		parser.findASTNodeByLine(codeStr,lineNumber);
		
		

	}

}
