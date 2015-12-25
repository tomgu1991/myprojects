package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InstanceofExpression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBInstanceofExpression extends CBExpression {
	private CBExpression expression;
	private String type;
	public CBInstanceofExpression(InstanceofExpression n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getLeftOperand());
		type = n.getRightOperand().toString();
	}
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBInstanceofExpression)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}

		CBInstanceofExpression temTar = (CBInstanceofExpression)tar;
		MapUtil.addTokenMapping(tokenMap, type, temTar.getType(), nodemap, e);
		expression.mapTokens(temTar.getExpression(), tokenMap, nodemap, e);
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
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

}
