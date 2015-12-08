package com.tomgu.report;

public class GenerateBugReport {
	
	/**
	 * TODO generate bug report
	 * @param type
	 * @param ref
	 * @param tar
	 * @param recommendation
	 * @return
	 */
	public static String generateBugReport(String type,String ref,String tar
			, String recommendation){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Bug Type:\n");
		buffer.append(type+"\n");
		buffer.append("Ref Code:\n");
		buffer.append(ref+"\n");
		buffer.append("Target Code:\n");
		buffer.append(tar+"\n"); 
		buffer.append("Recommendation:\n");
		buffer.append(recommendation);
		
		
		
		return buffer.toString();
	}
}
