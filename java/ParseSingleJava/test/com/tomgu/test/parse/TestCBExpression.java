package com.tomgu.test.parse;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;

import com.tomgu.entity.astnode.expression.CBArrayAccess;
import com.tomgu.parse.JavaParser;

public class TestCBExpression {

	public static void main(String[] args) {
		int[] array = new int[]{1,2,3};
		int tem;
		int index = 1;
		tem = array[index+1];
		
		String filePath = "./test/com/tomgu/test/parse/TestCBExpression.java";
		JavaParser parser = new JavaParser();
		CompilationUnit cuRef = parser.parseCompilationUnit(filePath);
		ASTNode nodeRef = parser.getMinCoveredASTNode(filePath, cuRef,
				18);
		System.out.println(nodeRef.toString());
		ArrayAccess a = (ArrayAccess) ((Assignment) ((ExpressionStatement)nodeRef).getExpression()).getRightHandSide();
		CBArrayAccess arrayAccess = new CBArrayAccess(a);
		System.out.println(arrayAccess.toCBString());
		System.out.println(arrayAccess.getArray().toCBString());
		System.out.println(arrayAccess.getIndex().toCBString());
	}

}
