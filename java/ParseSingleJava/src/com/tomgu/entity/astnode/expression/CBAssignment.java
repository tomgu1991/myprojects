package com.tomgu.entity.astnode.expression;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

public class CBAssignment extends CBExpression {
	private CBExpression leftExpression;
	private CBExpression rightExpression;
	private String assignmentOperator;
	public CBAssignment(Assignment n) {
		super(n);
		leftExpression = (CBExpression) CBASTNodeBuilder.build(n.getLeftHandSide());
		rightExpression = (CBExpression) CBASTNodeBuilder.build(n.getRightHandSide());
		assignmentOperator = n.getOperator().toString(); 
	}
	
	
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> map) {
		if(! (tar instanceof CBAssignment)){
			MapUtil.addTokenMapping(map,toCBString(),tar.toCBString());
			return;
		}
		
		CBAssignment temTar = (CBAssignment)tar;
		
		//left expression
		leftExpression.mapTokens(temTar.getLeftExpression(), map);
		//operator
		MapUtil.addTokenMapping(map, assignmentOperator, temTar.getAssignmentOperator());
		//right expression
		rightExpression.mapTokens(temTar.getRightExpression(), map);
		
		
		
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
	 * @return the assignmentOperator
	 */
	public String getAssignmentOperator() {
		return assignmentOperator;
	}

	
	
}
