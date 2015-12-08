package com.tomgu.util;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.util.statement.BlockOperator;

public class ASTNodeOperator {

	/**
	 * TODO get ASTNode body statement list
	 * @param node
	 * @return
	 */
	public static List<Statement> getASTNodeBodyStatementList(ASTNode node) {
		if(node instanceof Block){
			return BlockOperator.getStatementList((Block)node);
		}
		return null;
	}
	
}
