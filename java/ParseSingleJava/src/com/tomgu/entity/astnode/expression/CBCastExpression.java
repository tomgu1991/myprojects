package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Type;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

public class CBCastExpression extends CBExpression {
	private Type type;
	private CBExpression expression;
	
	public CBCastExpression(CastExpression n) {
		super(n);
		type = n.getType();
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
	}

	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> map) {
		// ASTNode type not match
		if(! (tar instanceof CBCastExpression)){
			MapUtil.addTokenMapping(map,toCBString(),tar.toCBString());
			return;
		}
		
		CBCastExpression temTar = (CBCastExpression)tar;
		
		// map type
		MapUtil.addTokenMapping(map, type.toString(), temTar.getType().toString());
		
		// map expression
		expression.mapTokens(temTar.getExpression(), map);
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
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the expression
	 */
	public CBExpression getExpression() {
		return expression;
	}
	
	
	
	
}
