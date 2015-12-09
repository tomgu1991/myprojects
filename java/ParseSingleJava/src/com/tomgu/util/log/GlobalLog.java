package com.tomgu.util.log;

public class GlobalLog {
	public static void LogClonePairInfo(String fileRef, String fileTar,
			int[] linesRef, int[] linesTar){
		StringBuffer buffer = new StringBuffer();
		buffer.append("file Ref: " + fileRef + "\n");
		buffer.append("lines in Ref: " + linesRef[0]+"-"+linesRef[linesRef.length-1] + "\n");
		
		buffer.append("fileTar : " + fileTar + "\n");
		buffer.append("lines in Tar: " + linesTar[0]+"-"+linesTar[linesTar.length-1] + "\n");
		System.out.println(buffer.toString());
	}
	
	public static void LogProcess(String str){
		System.out.println(str);
	}
}
