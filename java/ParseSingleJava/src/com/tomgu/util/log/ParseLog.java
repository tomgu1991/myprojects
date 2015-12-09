package com.tomgu.util.log;

import org.eclipse.jdt.core.dom.ASTNode;

public class ParseLog {
	public static void LogGetMinASTNodeByLinesLog(String filePath, int[] lines
			,ASTNode node){
		StringBuffer buffer = new StringBuffer();
		buffer.append(filePath + " : " + lines[0] + "-" + lines[lines.length-1]
				+ " smallest covering ASTNode is \n");
		buffer.append(node.toString());
		System.out.println(buffer.toString());
	}
}
