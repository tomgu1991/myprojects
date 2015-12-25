package com.tomgu.entity.astnode.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBSuperMethodInvocation extends CBExpression {
	private String name;
	private List<CBExpression> arguments;
	public CBSuperMethodInvocation(SuperMethodInvocation n) {
		super(n);
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
		if(! (tar instanceof CBSuperMethodInvocation)){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBSuperMethodInvocation temTar = (CBSuperMethodInvocation)tar;
		
		MapUtil.addTokenMapping(tokenMap, name, temTar.getName(),nodemap,e);
		
		if(name.equals(temTar.getName()) || arguments.size() == temTar.getArguments().size()){
			for(int index=0;index<arguments.size();index++)
				arguments.get(index).mapTokens(temTar.getArguments().get(index), tokenMap,nodemap,e);
		}
		else{
			//TODO if not the same method and size of arguments differ how to deal with the arguments
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
