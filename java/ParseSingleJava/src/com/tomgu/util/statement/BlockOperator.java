package com.tomgu.util.statement;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

public class BlockOperator {

	/**
	 * TODO get block node all sub statements
	 * @param node
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Statement> getStatementList(Block node) {
		return node.statements();
	}
}
