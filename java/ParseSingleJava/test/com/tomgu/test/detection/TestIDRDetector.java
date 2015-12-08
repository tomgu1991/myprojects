package com.tomgu.test.detection;

import org.eclipse.jdt.core.dom.ASTNode;

import com.tomgu.detecion.IDRDetector;
import com.tomgu.parse.JavaParser;

public class TestIDRDetector {

	public static void main(String[] args) {
		//TODO get clone node pair lines from clone deteciton result
		String filePath = "./source/bug.java";
		//if line contains } remove this line
		int[] linesRef = {218,219,220};
		int[] linesTar = {227,228,229};
		
		//TODO get minest ASTNode covering largest lines in the fragments
		JavaParser parser = new JavaParser();
		ASTNode nodeRef = parser.getMinASTNodeByLines(filePath,linesRef);
		ASTNode nodeTar = parser.getMinASTNodeByLines(filePath,linesTar);
		
		// TODO get detection result
		String result = IDRDetector.IDRDetection(nodeRef, nodeTar);
		System.out.println(result);

	}

}
