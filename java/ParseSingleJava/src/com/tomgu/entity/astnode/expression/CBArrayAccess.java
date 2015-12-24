package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Expression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;
/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBArrayAccess extends CBExpression {
	private CBExpression array;
	private CBExpression index;

	public CBArrayAccess(ArrayAccess n) {
		super(n);
		array = (CBExpression) CBASTNodeBuilder.build(n.getArray());
		index = (CBExpression) CBASTNodeBuilder.build(n.getIndex());
	}





	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		// TODO Auto-generated method stub
		if(! (tar instanceof CBArrayAccess)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}

		CBArrayAccess temTar = (CBArrayAccess)tar;
		//getArray expression
		array.mapTokens(temTar.getArray(), tokenMap,nodemap,e);
		//index expression
		index.mapTokens(temTar.getIndex(), tokenMap, nodemap, e);
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
	 * @return the getArray
	 */
	public CBExpression getArray() {
		return array;
	}

	/**
	 * @return the getIndex
	 */
	public CBExpression getIndex() {
		return index;
	}


}
