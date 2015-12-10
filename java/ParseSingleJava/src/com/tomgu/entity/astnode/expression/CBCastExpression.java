package com.tomgu.entity.astnode.expression;

import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Type;

import com.tomgu.util.astnode.CBASTNodeBuilder;

public class CBCastExpression extends CBExpression {
	private Type type;
	private CBExpression expression;
	
	public CBCastExpression(CastExpression n) {
		super(n);
		type = n.getType();
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
	}
}
