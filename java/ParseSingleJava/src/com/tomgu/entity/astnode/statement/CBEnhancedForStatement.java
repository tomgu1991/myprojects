package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.entity.astnode.statement.variabledeclaration.CBSingleVariableDeclaration;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */

public class CBEnhancedForStatement extends CBStatement {
	private CBSingleVariableDeclaration parameter;
	private CBExpression expression;
	private CBStatement statement;
	
	public CBEnhancedForStatement(EnhancedForStatement n) {
		super(n);
		parameter = (CBSingleVariableDeclaration) CBASTNodeBuilder.build(n.getParameter());
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		statement = (CBStatement) CBASTNodeBuilder.build(n.getBody());
	}

	/**
	 * @return the parameter
	 */
	public CBSingleVariableDeclaration getParameter() {
		return parameter;
	}

	/**
	 * @return the expression
	 */
	public CBExpression getExpression() {
		return expression;
	}

	/**
	 * @return the statement
	 */
	public CBStatement getStatement() {
		return statement;
	}

}
