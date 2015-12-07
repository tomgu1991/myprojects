package com.tomgu.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {
	public static String readFileToString(String filePath){
		String result = "";
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
			String line = "";
			while ( (line=reader.readLine()) != null ){
				buffer.append(line);
			}
			result = buffer.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String readFileToStringByChar(String filePath){
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			char[] buf = new char[10];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
//				System.out.println(numRead);
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
	 
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  fileData.toString();	
	}
	
	public static int getPositionByLineNumber(String filePath, int lineNumber){
		int result = 0;
		StringBuffer buffer = new StringBuffer();
		int lineFlag = 1;
		try {
			BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
			String line = "";
			while ( (line=reader.readLine()) != null ){
				System.out.println(line);
				System.out.println(lineFlag + ": " + result);
				if(lineFlag == lineNumber)
					return result;
				result += line.toCharArray().length + 1;
				lineFlag++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
