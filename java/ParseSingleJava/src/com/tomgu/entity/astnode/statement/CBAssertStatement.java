package com.tomgu.entity.astnode.statement;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBAssertStatement extends CBStatement {
	private CBExpression expression;
	private CBExpression message;
	
	public CBAssertStatement(AssertStatement n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		message = (CBExpression) CBASTNodeBuilder.build(n.getMessage());
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBAssertStatement) ){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBAssertStatement tarTem = (CBAssertStatement)tar;
		expression.mapTokens(tarTem.getExpression(), tokenMap, nodemap, e);
		message.mapTokens(tarTem.getMessage(), tokenMap, nodemap, e);
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
	 * @return the message
	 */
	public CBExpression getMessage() {
		return message;
	}

	
}
