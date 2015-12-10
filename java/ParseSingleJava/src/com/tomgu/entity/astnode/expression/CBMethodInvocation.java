package com.tomgu.entity.astnode.expression;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;

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

}
