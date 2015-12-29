package com.tomgu.test.parse;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import com.tomgu.entity.astnode.expression.CBArrayAccess;
import com.tomgu.entity.astnode.expression.CBBooleanLiteral;
import com.tomgu.parse.JavaParser;

public class TestCBExpression {

	public static void main(String[] args) {
		int[] array = new int[]{1,2,3};
		int tem;
		int index = 1;
		tem = array[index+1];
		
		boolean bV;
		bV= true;
		
		char c;
		c = 'c';
		
		index = c == 'd' ? 1+2 : 3*4;

		
		String filePath = "./test/com/tomgu/test/parse/TestCBExpression.java";
		JavaParser parser = new JavaParser();
		CompilationUnit cuRef = parser.parseCompilationUnit(filePath);
		
//		testArrayAccess(filePath,cuRef,parser);
		testBooleanLiteral(filePath,cuRef,parser);

	}

	private static void testBooleanLiteral(String filePath,
			CompilationUnit cuRef, JavaParser parser) {
		ASTNode nodeRef = parser.getMinCoveredASTNode(filePath, cuRef,
				27);
		System.out.println(nodeRef.toString());
		BooleanLiteral b =  (BooleanLiteral) ((Assignment) ((ExpressionStatement)nodeRef).getExpression()).getRightHandSide();
		System.out.println(b.toString());
		CBBooleanLiteral cbB = new CBBooleanLiteral(b);
		System.out.println(cbB.toCBString());
		
		ExpressionStatement stmt = (ExpressionStatement)nodeRef;
		StructuralPropertyDescriptor stmtlocation = stmt.getLocationInParent();
		
		Object stmtGet = stmt.getParent().getStructuralProperty(stmtlocation);
		StructuralPropertyDescriptor blocation = b.getLocationInParent();
		Object bGet = b.getParent().getStructuralProperty(blocation);
		System.out.println(bGet.toString());
		
		
	}

	private static void testArrayAccess(String filePath, CompilationUnit cuRef, JavaParser parser) {
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
