package com.tomgu.entity.astnode;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;

public abstract class AbstractCBASTNode {
	protected ASTNode node;
	
	public AbstractCBASTNode(ASTNode n) {
		node = n;
	}
	
	abstract public void mapTokens(AbstractCBASTNode tar,Map<String,List> map);
	
	abstract public String toCBString();
}
