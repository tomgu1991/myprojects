package com.tomgu.entity.astnode.variabledeclaration;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.entity.astnode.statement.CBStatement;
import com.tomgu.entity.astnode.statement.CBVariableDeclarationStatement;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;


public class CBVariableDeclaratrionFragment extends CBVariableDeclaration {
	private String name;
	private CBExpression initializer;
	public CBVariableDeclaratrionFragment(VariableDeclarationFragment n) {
		super(n);
		name = n.getName().getFullyQualifiedName();
		initializer = (CBExpression) CBASTNodeBuilder.build(n.getInitializer());
	}
	
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> map) {
		if(! (tar instanceof CBVariableDeclaratrionFragment) ){
			MapUtil.addTokenMapping(map,toCBString(),tar.toCBString());
			return;
		}
		CBVariableDeclaratrionFragment temTar = (CBVariableDeclaratrionFragment)tar;
		MapUtil.addTokenMapping(map, name, temTar.getName());
		initializer.mapTokens(temTar.getInitializer(), map);
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
	 * @return the initializer
	 */
	public CBExpression getInitializer() {
		return initializer;
	}

	
	
}
