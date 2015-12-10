package com.tomgu.entity.astnode.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.variabledeclaration.CBVariableDeclaratrionFragment;
import com.tomgu.entity.astnode.visitor.CBVariableDeclarationStatementVisitor;

public class CBVariableDeclarationStatement extends CBStatement {
	private Type type;
	private List<CBVariableDeclaratrionFragment> fragmentList;
//	private CBVariableDeclarationStatementVisitor visitor;
	
	public CBVariableDeclarationStatement(VariableDeclarationStatement n) {
		super(n);
		type = n.getType();
		fragmentList = new ArrayList<>();
		for(int i = 0;i<n.fragments().size();i++){
			fragmentList.add(new CBVariableDeclaratrionFragment(
					(VariableDeclarationFragment) n.fragments().get(i)));
		}
	}

	/**
	 * VariableDeclarationStatement mapTokens
	 * map type
	 * map fragmentList
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> map) {
		// TODO Auto-generated method stub
		super.mapTokens(tar, map);
	}

	
	
}
