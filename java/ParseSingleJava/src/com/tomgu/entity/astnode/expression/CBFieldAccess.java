package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBFieldAccess extends CBExpression {
	private CBExpression expression;
	private CBName fieldName;
	
	public CBFieldAccess(FieldAccess n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		fieldName = (CBName) CBASTNodeBuilder.build(n.getName());
	}

	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBFieldAccess)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBFieldAccess temTar = (CBFieldAccess)tar;
		expression.mapTokens(temTar.getExpression(), tokenMap, nodemap, e);
		fieldName.mapTokens(temTar.getFieldName(), tokenMap, nodemap, e);
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
	 * @return the fieldName
	 */
	public CBName getFieldName() {
		return fieldName;
	}
	
	

}
