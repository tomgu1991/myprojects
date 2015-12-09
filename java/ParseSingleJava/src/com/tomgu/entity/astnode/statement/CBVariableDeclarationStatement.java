package com.tomgu.entity.astnode.statement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.visitor.CBVariableDeclarationStatementVisitor;

public class CBVariableDeclarationStatement extends CBASTNode {
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

	
}
