package com.tomgu.entity.astnode.statement;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.util.MapUtil;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBBreakStatement extends CBStatement {
	private String identifier;
	
	public CBBreakStatement(BreakStatement n) {
		super(n);
		if(n.getLabel() == null){
			identifier = null;
		}else {
			identifier = n.getLabel().toString();
		}
	}

	
	
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBBreakStatement) ){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBBreakStatement tarTem = (CBBreakStatement)tar;
		if(identifier != null || tarTem.getIdentifier() != null){
			MapUtil.addTokenMapping(tokenMap, identifier, tarTem.getIdentifier()
					, nodemap, e);
		}
		
	}



	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#toCBString()
	 */
	@Override
	public String toCBString() {
		// TODO Auto-generated method stub
		return "Break";
	}



	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	
}
