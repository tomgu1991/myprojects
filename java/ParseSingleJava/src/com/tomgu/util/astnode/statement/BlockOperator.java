package com.tomgu.util.astnode.statement;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

public class BlockOperator {

	/**
	 * TODO get block node all sub statements
	 * in IF return inflxExpression
	 * in Metho return method parameter
	 * @param node
	 * @return
	 */
	public static List getStatementList(Block node) {
		return node.statements();
	}
}
