package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;

import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */

public class CBWhileStatement extends CBStatement {
	private CBExpression expression;
	private CBStatement body; 
	public CBWhileStatement(WhileStatement n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		body = (CBStatement) CBASTNodeBuilder.build(n.getBody());
	}

}
