package com.tomgu.entity.astnode.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.statement.CBVariableDeclarationStatement;
import com.tomgu.entity.astnode.variabledeclaration.CBVariableDeclaratrionFragment;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

public class CBMethodInvocation extends CBExpression {
	private CBExpression expression;
	private String name;
	private List<CBExpression> arguments;
	public CBMethodInvocation(MethodInvocation n) {
		super(n);
		expression = (CBExpression) CBASTNodeBuilder.build(n.getExpression());
		name = n.getName().getFullyQualifiedName();
		arguments = new ArrayList<CBExpression>();
		for(Object ex: n.arguments()){
			arguments.add((CBExpression) 
					CBASTNodeBuilder.build((Expression)ex));
		}
	}

	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String,List> tokenMap,
			Map<String,List<ASTNodeMappingElement>> nodemap, ASTNodeMappingElement e) {
		// ASTNode type not match
		if(! (tar instanceof CBMethodInvocation)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBMethodInvocation temTar = (CBMethodInvocation)tar;
		
		// method name not match
		// not used when in mapping token
		//		if(!name.equals(temTar.getName())){
//			MapUtil.addTokenMapping(map,toCBString(),tar.toCBString());
//			return;
//		}
		
		MapUtil.addTokenMapping(tokenMap, name, temTar.getName(),nodemap,e);
		
		expression.mapTokens(temTar.getExpression(), tokenMap,nodemap,e);
		
		int minSize = arguments.size();
		if(temTar.arguments.size()<minSize)
			minSize = temTar.arguments.size();
		for(int index=0;index<minSize;index++)
			arguments.get(index).mapTokens(temTar.getArguments().get(index), tokenMap,nodemap,e);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the arguments
	 */
	public List<CBExpression> getArguments() {
		return arguments;
	}
	
	
}
