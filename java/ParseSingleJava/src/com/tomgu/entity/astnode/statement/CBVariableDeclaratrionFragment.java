package com.tomgu.entity.astnode.statement;

import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;


public class CBVariableDeclaratrionFragment extends CBASTNode {
	private String name;
	private Expression expression;
	public CBVariableDeclaratrionFragment(VariableDeclarationFragment n) {
		super(n);
		name = n.getName().getFullyQualifiedName();
		expression = n.getInitializer();
	}


}
