package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;

public class CBName extends CBExpression {
	private Name name;
	
	public CBName(Name n) {
		super(n);
		name = n;
	}

	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String,List> tokenMap,
			Map<String,List<ASTNodeMappingElement>> nodemap, ASTNodeMappingElement e) {
		// ASTNode type not match
		if(! (tar instanceof CBName)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBName temTar = (CBName)tar;
		
		MapUtil.addTokenMapping(tokenMap, name.getFullyQualifiedName(), 
				temTar.getName().getFullyQualifiedName(),nodemap,e);
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
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	
	
}
