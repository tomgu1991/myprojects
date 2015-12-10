package com.tomgu.entity.astnode.variabledeclaration;

import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.entity.astnode.statement.CBStatement;
import com.tomgu.util.astnode.CBASTNodeBuilder;


public class CBVariableDeclaratrionFragment extends CBVariableDeclaration {
	private String name;
	private CBExpression initializer;
	public CBVariableDeclaratrionFragment(VariableDeclarationFragment n) {
		super(n);
		name = n.getName().getFullyQualifiedName();
		initializer = (CBExpression) CBASTNodeBuilder.build(n.getInitializer());
	}


}
