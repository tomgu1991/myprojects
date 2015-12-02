package com.tomgu;

import java.io.File;

import com.tomgu.parse.JavaParser;
import com.tomgu.util.FileReaderUtil;

public class ParseJavaFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "./source/bug.java";
		File file = new File(filePath);
		System.out.println(filePath + " exist? "+file.exists());
		String codeStr = "";
		//codeStr = FileReaderUtil.readFileToString(filePath);
		//System.out.println(codeStr);
		codeStr = FileReaderUtil.readFileToStringByChar(filePath);
//		System.out.println(codeStr);
		JavaParser parser = new JavaParser();
		parser.parse(codeStr);
		
		

	}

}
