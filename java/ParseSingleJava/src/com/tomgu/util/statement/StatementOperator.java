package com.tomgu.util.statement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.StatementMappingElement;

public class StatementOperator {

	/**
	 * TODO get statement mapping relationship
	 * Note: 
	 * (1) define equivalence class 
	 * (2) define standard statement description for each statement
	 * (3) calculate similarity > threshold 
	 * @param stListRef
	 * @param stListTar
	 * @return
	 */
	public static List<StatementMappingElement> getMappingList(
			List<Statement> stListRef, List<Statement> stListTar) {
		List<StatementMappingElement> list = new ArrayList<StatementMappingElement>();
		StatementMappingElement element = 
				new StatementMappingElement(stListRef.get(3), stListTar.get(4));
		list.add(element);
		return list;
	}

}
