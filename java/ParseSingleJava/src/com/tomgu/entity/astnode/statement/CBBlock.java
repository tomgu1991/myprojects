package com.tomgu.entity.astnode.statement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */
public class CBBlock extends CBStatement {
	private List list;
	
	public CBBlock(Block n) {
		super(n);
		list = new ArrayList();
		for(int index=0;index < n.statements().size();index++){
			list.add(
					(CBStatement) CBASTNodeBuilder.build(
							(Statement)n.statements().get(index)));
		}
	}
	
	

}
