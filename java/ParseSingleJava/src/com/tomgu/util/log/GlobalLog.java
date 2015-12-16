package com.tomgu.util.log;

import com.tomgu.property.GlobalProperty;

public class GlobalLog {
	public static void LogClonePairInfo(String fileRef, String fileTar,
			int[] linesRef, int[] linesTar){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Clone Pair Result:\n");
		buffer.append("file Ref: " + fileRef + "\n");
		buffer.append("lines in Ref: " + linesRef[0]+"-"+linesRef[linesRef.length-1] + "\n");
		
		buffer.append("fileTar : " + fileTar + "\n");
		buffer.append("lines in Tar: " + linesTar[0]+"-"+linesTar[linesTar.length-1]);
		simplePrint(buffer.toString());
	}
	
	public static void LogBugResult(String str) {
		simplePrint(str);
		
	}

	public static void LogProcess(String str){
		print(str);
		
	}
	
	private static void print(String str){
		System.out.println(GlobalProperty.SEPARATOR);
		System.out.println(str);
		System.out.println(GlobalProperty.SEPARATOR);
	}


	
	private static void simplePrint(String str) {
		System.out.println(str+"\n");
		
	}
}
