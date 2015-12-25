package com.tomgu.entity.astnode.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBInfixExpression extends CBExpression {
	private CBExpression leftExpression;
	private CBExpression rightExpression;
	private String operator;
	private List<CBExpression> extendedOperandsList;
	
	public CBInfixExpression(InfixExpression n) {
		super(n);
		leftExpression = (CBExpression) CBASTNodeBuilder.build(n.getLeftOperand());
		rightExpression = (CBExpression) CBASTNodeBuilder.build(n.getRightOperand());
		operator = n.getOperator().toString();
		extendedOperandsList = new ArrayList<CBExpression>();
		if(n.hasExtendedOperands()){
			Expression e;
			for(int index=0;index<n.extendedOperands().size();index++){
				e = (Expression) n.extendedOperands().get(index);
				extendedOperandsList.add((CBExpression) CBASTNodeBuilder.build(e));
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBInfixExpression)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBInfixExpression temTar = (CBInfixExpression)tar;
		leftExpression.mapTokens(temTar.getLeftExpression(), tokenMap, nodemap, e);
		rightExpression.mapTokens(temTar.getRightExpression(), tokenMap, nodemap, e);
		MapUtil.addTokenMapping(tokenMap, operator, temTar.getOperator(),nodemap,e);
		//TODO if have the same size
		if(extendedOperandsList.size() == temTar.getExtendedOperandsList().size()){
			for(int index=0;index<extendedOperandsList.size();index++)
				extendedOperandsList.get(index).mapTokens(temTar.getExtendedOperandsList().get(index)
						, tokenMap, nodemap, e);
		}
		
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
	 * @return the leftExpression
	 */
	public CBExpression getLeftExpression() {
		return leftExpression;
	}

	/**
	 * @return the rightExpression
	 */
	public CBExpression getRightExpression() {
		return rightExpression;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @return the extendedOperandsList
	 */
	public List<CBExpression> getExtendedOperandsList() {
		return extendedOperandsList;
	}
	
	

}
