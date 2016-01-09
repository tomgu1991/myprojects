package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CatchClause;

import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.statement.variabledeclaration.CBSingleVariableDeclaration;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBCatchClause extends CBASTNode {
	private CBSingleVariableDeclaration exception;
	private CBBlock body;
	
	public CBCatchClause(CatchClause n) {
		super(n);
		exception = (CBSingleVariableDeclaration) CBASTNodeBuilder.build(n.getException());
		body = (CBBlock) CBASTNodeBuilder.build(n.getBody());
	}

}
