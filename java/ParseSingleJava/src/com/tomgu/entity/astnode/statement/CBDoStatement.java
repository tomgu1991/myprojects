package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBDoStatement extends CBStatement {
	private CBStatement statement;
	private CBExpression expression;
	
	public CBDoStatement(DoStatement n) {
		super(n);
		statement = (CBStatement) CBASTNodeBuilder.build(n.getBody());
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
	}

	/**
	 * @return the statement
	 */
	public CBStatement getStatement() {
		return statement;
	}

	/**
	 * @return the expression
	 */
	public CBExpression getExpression() {
		return expression;
	}

	
}
