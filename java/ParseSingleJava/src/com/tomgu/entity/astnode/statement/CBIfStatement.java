package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBIfStatement extends CBStatement {
	private CBExpression expression;
	private CBStatement thenStatement;
	private CBStatement elseStatement;
	
	public CBIfStatement(IfStatement n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		thenStatement = (CBStatement) CBASTNodeBuilder.build(n.getThenStatement());
		if(n.getElseStatement() != null)
		elseStatement = (CBStatement) CBASTNodeBuilder.build(n.getElseStatement());
	}

	/**
	 * @return the expression
	 */
	public CBExpression getExpression() {
		return expression;
	}

	/**
	 * @return the thenStatement
	 */
	public CBStatement getThenStatement() {
		return thenStatement;
	}

	/**
	 * @return the elseStatement
	 */
	public CBStatement getElseStatement() {
		return elseStatement;
	}

}
