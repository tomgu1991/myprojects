package com.tomgu.entity.astnode.expression;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;

import com.tomgu.util.astnode.CBASTNodeBuilder;

public class CBParenthesizedExpression extends CBExpression {
	private CBExpression expression;
	public CBParenthesizedExpression(ParenthesizedExpression n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
	}

}
