package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;

import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */

public class CBSychronizedStatement extends CBStatement {
	private CBExpression expression;
	private CBBlock block;
	
	public CBSychronizedStatement(SynchronizedStatement n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		block = (CBBlock) CBASTNodeBuilder.build(n.getBody());
	}

}
