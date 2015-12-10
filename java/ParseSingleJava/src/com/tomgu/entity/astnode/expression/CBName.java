package com.tomgu.entity.astnode.expression;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;

public class CBName extends CBExpression {
	private Name name;
	
	public CBName(Name n) {
		super(n);
		name = n;
	}

}
