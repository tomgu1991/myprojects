package com.tomgu.entity;

import org.eclipse.jdt.core.dom.Statement;

public class StatementMappingElement{
	private Statement ref;
	private Statement tar;
	public StatementMappingElement(Statement ref, Statement tar){
		this.ref = ref;
		this.tar = tar;
	}
	public Statement getRef() {
		return ref;
	}
	public Statement getTar() {
		return tar;
	}

	
}
