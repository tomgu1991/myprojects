package com.tomgu.util.astnode;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.statement.CBVariableDeclarationStatement;

public class CBASTNodeBuilder {
	public static AbstractCBASTNode buildCBASTNode(ASTNode node){
		AbstractCBASTNode result;
		switch(node.getNodeType()){
		case ASTNode.VARIABLE_DECLARATION_STATEMENT:
			result = new CBVariableDeclarationStatement((VariableDeclarationStatement) node);
			break;
		default:
			result = new CBASTNode(node);
		}
		
		return result;
	}
}
