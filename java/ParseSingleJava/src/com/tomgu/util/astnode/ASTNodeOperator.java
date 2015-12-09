package com.tomgu.util.astnode;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.util.astnode.statement.BlockOperator;

public class ASTNodeOperator {

	/**
	 * TODO get ASTNode body statement list
	 * @param node
	 * @return
	 */
	public static List getASTNodeBodyStatementList(ASTNode node) {
		switch(node.getNodeType()){
		case ASTNode.BLOCK:
			return BlockOperator.getStatementList((Block)node);
		default:
			return null;
		}
		
	}
	
}
