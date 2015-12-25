package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.PostfixExpression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBPostfixExpression extends CBExpression {
	private CBExpression expression;
	private String operator;
	public CBPostfixExpression(PostfixExpression n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getOperand());
		operator = n.getOperator().toString();
	}
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBPostfixExpression)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBPostfixExpression temTar = (CBPostfixExpression)tar;
		expression.mapTokens(temTar.getExpression(), tokenMap, nodemap, e);
		
	}
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#toCBString()
	 */
	@Override
	public String toCBString() {
		return expression.toCBString()+operator;
	}
	/**
	 * @return the expression
	 */
	public CBExpression getExpression() {
		return expression;
	}
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	
}
