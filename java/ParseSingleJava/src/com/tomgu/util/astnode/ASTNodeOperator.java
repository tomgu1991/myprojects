package com.tomgu.util.astnode;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
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
	
	/**
	 * mapping clone node pair tokens
	 * @param map
	 * @param nodeMap 
	 * @param e
	 */
	public static void getASTNodeTokenMapping(Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodeMap, ASTNodeMappingElement e){
		ASTNode ref = e.getRef();
		ASTNode tar = e.getTar();
		AbstractCBASTNode refCBASTNode = CBASTNodeBuilder.build(ref);
		AbstractCBASTNode tarCBASTNode = CBASTNodeBuilder.build(tar);
		refCBASTNode.mapTokens(tarCBASTNode, tokenMap,nodeMap,e);
		
	}
	
}
