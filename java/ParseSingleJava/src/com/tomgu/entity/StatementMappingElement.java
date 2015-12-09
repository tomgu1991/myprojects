package com.tomgu.entity;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Statement;

public class StatementMappingElement{
	private ASTNode ref;
	private ASTNode tar;
	public StatementMappingElement(ASTNode ref, ASTNode tar){
		this.ref = ref;
		this.tar = tar;
	}
	public ASTNode getRef() {
		return ref;
	}
	public ASTNode getTar() {
		return tar;
	}

	
}
