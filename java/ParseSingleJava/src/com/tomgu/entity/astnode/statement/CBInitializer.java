package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Initializer;

import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */

public class CBInitializer extends CBASTNode {
	private CBBlock block;
	public CBInitializer(Initializer n) {
		super(n);
		block = (CBBlock) CBASTNodeBuilder.build(n);
	}

}
