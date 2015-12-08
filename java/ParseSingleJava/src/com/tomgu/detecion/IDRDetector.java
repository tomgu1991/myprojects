package com.tomgu.detecion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.StatementMappingElement;
import com.tomgu.property.GlobalProperty;
import com.tomgu.report.GenerateBugReport;
import com.tomgu.report.entity.IDRReportElement;
import com.tomgu.util.ASTNodeOperator;
import com.tomgu.util.statement.StatementOperator;

public class IDRDetector {

	/**
	 * TODO detecting inconsistent renaming bug by nodeRef and nodeTar
	 * 1. build clone node pair
	 * 2. get statement pair tokens
	 * 3. match the tokens
	 * @param nodeRef
	 * @param nodeTar
	 * @return
	 */
	public static String IDRDetection(ASTNode nodeRef, ASTNode nodeTar){
		String result = null;

		// TODO get statement list of nodeRef and nodeTar
		List<Statement> stListRef = ASTNodeOperator.getASTNodeBodyStatementList(nodeRef);
		List<Statement> stListTar = ASTNodeOperator.getASTNodeBodyStatementList(nodeTar);

		// TODO get statement mapping
		List<StatementMappingElement> mappingList = StatementOperator.getMappingList(stListRef,stListTar);

		// TODO get token mapping relationship
		Map<String,List<String>> tokenMap = new HashMap<>();
		buildMapping(tokenMap,mappingList);
		IDRReportElement reportElement = checkMap(tokenMap);

		if(reportElement != null){
			result = GenerateBugReport.generateBugReport(GlobalProperty.IDRBug,
					nodeRef.toString(), nodeTar.toString(), reportElement.toString());
		}
		return result;
	}

	/**
	 * check whether there is a token in ref maps several tokens in tar
	 * NOTE: if more than 2 unmatched, it may be intentional by the developer  
	 * @param tokenMap
	 * @return
	 */
	private static IDRReportElement checkMap(Map<String, List<String>> tokenMap) {
		// TODO Auto-generated method stub
		
		for(String key: tokenMap.keySet()){
			if(tokenMap.get(key).size() >= 2){
				IDRReportElement ele = new IDRReportElement();
				ele.ref = key;
				ele.tarList = tokenMap.get(key);
				return ele;
			}
		}
		return null;
	}

	/**
	 * TODO generate token map
	 * Token(ref) : token1,token2(tar)
	 * @param tokenMap
	 * @param mappingList
	 */
	private static void buildMapping(Map<String, List<String>> tokenMap,
			List<StatementMappingElement> mappingList) {
		// TODO Auto-generated method stub
		tokenMap.put("rhsName", new ArrayList<String>());
		tokenMap.get("rhsName").add("rhsType");
		tokenMap.get("rhsName").add("lhsType");
	}
}
