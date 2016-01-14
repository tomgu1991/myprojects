package com.tomgu.test.tem;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.NodeFinder;

import com.tomgu.parse.JavaParser;
import com.tomgu.util.FileReaderUtil;

public class TestFinderCovered {

	public TestFinderCovered() {
		
	}
	public static void main(String[] str){
		//test1();
		test2();
		
	}
	private static void test2() {
		String filePathRef = "./source/WrongMapIterator.java";
		int[] linesRef = {147,149,151,152,153,154}; 
		JavaParser parser = new JavaParser();
		CompilationUnit cu = parser.parseCompilationUnit(filePathRef);
		ASTNode root = cu.getRoot();
		ASTNode coveredNode;
		
		for(int line:linesRef){
			System.out.println(line);
			int length = FileReaderUtil.getLineLength(filePathRef,line);
			int position = FileReaderUtil.getPositionByLineNumber(filePathRef, line);
			System.out.println(position+";"+length);
			coveredNode = NodeFinder.perform(root, position, length);
			
//			NodeFinder finder = new NodeFinder(root, position, length);
//			coveredNode = finder.getCoveredNode();
			System.out.println(coveredNode);
//			if(coveredNode != null)
//				System.out.println(cu.getLineNumber(coveredNode.getStartPosition()));
		}

		
	}
	private static void test1() {
		String filePathRef = "./source/bug.java";
		int[] linesRef = {218,219,220}; 
		JavaParser parser = new JavaParser();
		CompilationUnit cu = parser.parseCompilationUnit(filePathRef);
		ASTNode root = cu.getRoot();
		ASTNode coveredNode;
		for(int line:linesRef){
			System.out.println(line);
			int length = FileReaderUtil.getLineLength(filePathRef,line);
			int position = FileReaderUtil.getPositionByLineNumber(filePathRef, line);
			System.out.println(position+";"+length);
			coveredNode = NodeFinder.perform(root, position, length);
			System.out.println(coveredNode.toString());
		}
		
	}
}
