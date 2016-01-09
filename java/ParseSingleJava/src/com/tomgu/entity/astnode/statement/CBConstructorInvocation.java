package com.tomgu.entity.astnode.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.util.MapUtil;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBConstructorInvocation extends CBStatement {
	private List list;
	public CBConstructorInvocation(ConstructorInvocation n) {
		super(n);
		list = new ArrayList();
		for(int index=0;index<n.arguments().size();index++){
			list.add(
					CBASTNodeBuilder.build((Expression) n.arguments().get(index)));
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.tomgu.entity.astnode.CBASTNode#mapTokens(com.tomgu.entity.astnode.AbstractCBASTNode, java.util.Map, java.util.Map, com.tomgu.entity.ASTNodeMappingElement)
	 */
	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodemap,
			ASTNodeMappingElement e) {
		if(! (tar instanceof CBConstructorInvocation) ){
			MapUtil.addTokenMapping(tokenMap,toCBString(),tar.toCBString()
					,nodemap,e);
			return;
		}
		
		CBConstructorInvocation tarTem = (CBConstructorInvocation)tar;
		//TODO only the same Class type check this part
		
		//only same arguments size checks
		if(list.size() == tarTem.getList().size()){
			for(int index = 0;index < list.size();index++)
				((CBExpression)list.get(index)).mapTokens(
						(CBExpression)tarTem.getList().get(index), tokenMap, nodemap, e);
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
	 * @return the list
	 */
	public List getList() {
		return list;
	}

	
}
