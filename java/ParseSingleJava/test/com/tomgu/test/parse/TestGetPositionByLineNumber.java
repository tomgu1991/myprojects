package com.tomgu.test.parse;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

import com.tomgu.parse.JavaParser;
import com.tomgu.test.util.TestFileReaderUtil;
import com.tomgu.util.FileReaderUtil;

public class TestGetPositionByLineNumber {

	public static void main(String[] args) {
	
		int lineNumber = 227;
		String filePath = "./source/bug.java";
		int position = FileReaderUtil.getPositionByLineNumber(filePath, lineNumber);
		int length = FileReaderUtil.getLineLength(filePath,lineNumber);
		System.out.println("line " + lineNumber +" position is : " + position);
		System.out.println("length is: "+length );
		JavaParser parser = new JavaParser();
		String codeStr = TestFileReaderUtil.getSourceCode(filePath);
		CompilationUnit cu = parser.parseCompilationUnit(filePath);
		ASTNode node = parser.getMinASTNodeByPosition(cu, position, length);
//		System.out.println(node.toString());
		System.out.println(node.getNodeType() == ASTNode.BLOCK);
		Block bNode = (Block)node;
		StructuralPropertyDescriptor location = bNode.getLocationInParent();
		System.out.println(location.toString());
		System.out.println(location.isChildProperty());
		System.out.println(location.getNodeClass().toString());
		
		List<Statement> list = bNode.statements();
		System.out.println(list.size());
		for(Statement stmt: list){
			System.out.println(stmt.toString());
			System.out.println(stmt.getClass().toString());
		}
	}

}
