package com.tomgu.entity.astnode.statement;

import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.util.astnode.CBASTNodeBuilder;

/**
 * TODO need test
 * @author guzuxing
 *
 */

public class CBLabeledStatement extends CBStatement {
	private String label;
	private CBStatement body;
	public CBLabeledStatement(LabeledStatement n) {
		super(n);
		label = n.getLabel().toString();
		body = (CBStatement) CBASTNodeBuilder.build(n.getBody());
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @return the body
	 */
	public CBStatement getBody() {
		return body;
	}

	
}
