package com.tomgu.entity.astnode.visitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;

public class CBVariableDeclarationStatementVisitor extends ASTVisitor {
	private List<String> tokenList;
	public CBVariableDeclarationStatementVisitor(){
		super();
		tokenList = new ArrayList<String>();
	}
	
	public List<String> getTokenList(){
		return tokenList;
	}
	
	
	
	/**
	 * add simpleType into list
	 */
	@Override
	public boolean visit(SimpleName node) {
		tokenList.add(node.getIdentifier());
		return true;
	}
	
}
