package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.Expression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;

/**
 * TODO test
 * @author guzuxing
 *
 */
public class CBCharacterLiteral extends CBExpression {
	private char value;
	public CBCharacterLiteral(CharacterLiteral n) {
		super(n);
		value = n.charValue();
	}
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBCharacterLiteral)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBCharacterLiteral temTar = (CBCharacterLiteral)tar;
		MapUtil.addTokenMapping(tokenMap, toCBString(), temTar.toCBString()
				,nodemap,e);
		
	}
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#toCBString()
	 */
	@Override
	public String toCBString() {
		// TODO Auto-generated method stub
		return "Char";
	}
	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	
	
	
	
}
