package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

public class CBParenthesizedExpression extends CBExpression {
	private CBExpression expression;
	public CBParenthesizedExpression(ParenthesizedExpression n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
	}

	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String,List> tokenMap,
			Map<String,List<ASTNodeMappingElement>> nodemap, ASTNodeMappingElement e) {
		// ASTNode type not match
		if(! (tar instanceof CBParenthesizedExpression)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		CBParenthesizedExpression temTar = (CBParenthesizedExpression)tar;
		expression.mapTokens(temTar.getExpression(), tokenMap,nodemap,e);
	}
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#toCBString()
	 */
	@Override
	public String toCBString() {
		// TODO Auto-generated method stub
		return super.toCBString();
	}

	/**
	 * @return the expression
	 */
	public CBExpression getExpression() {
		return expression;
	}


}
