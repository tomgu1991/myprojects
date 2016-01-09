package com.tomgu.entity.astnode.statement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

import com.tomgu.entity.astnode.expression.CBVariableDeclarationExpression;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBTryStatement extends CBStatement {
	private List<CBVariableDeclarationExpression> resources;
	private CBBlock bodyBlock;
	private CBBlock finallyBlock;
	private List<CBCatchClause> catchClause;
	
	
	public CBTryStatement(TryStatement n) {
		super(n);
		bodyBlock = (CBBlock) CBASTNodeBuilder.build(n.getBody());
		if(n.resources() != null ){
			resources = new ArrayList<CBVariableDeclarationExpression>();
			for(int index=0;index<n.resources().size();index++){
				resources.add((CBVariableDeclarationExpression)
					(CBASTNodeBuilder.build((VariableDeclarationExpression)n.resources().get(index))));
			}
		}
		if(n.catchClauses()!= null){
			catchClause = new ArrayList<CBCatchClause>();
			for(int index=0;index<n.resources().size();index++){
				catchClause.add((CBCatchClause)
					(CBASTNodeBuilder.build((CatchClause)n.resources().get(index))));
			}
		}
		if(n.getFinally() != null)
			finallyBlock = (CBBlock) CBASTNodeBuilder.build(n.getFinally());
	}

}
