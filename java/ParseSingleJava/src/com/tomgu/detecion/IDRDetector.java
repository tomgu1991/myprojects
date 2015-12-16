package com.tomgu.detecion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.property.GlobalProperty;
import com.tomgu.report.GenerateBugReport;
import com.tomgu.report.entity.IDRReportElement;
import com.tomgu.util.astnode.ASTNodeOperator;
import com.tomgu.util.astnode.statement.StatementOperator;
import com.tomgu.util.log.GlobalLog;

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
		GlobalLog.LogProcess(GlobalProperty.EXTRACTSTATEMENT);
		List stListRef = ASTNodeOperator.getASTNodeBodyStatementList(nodeRef);
		List stListTar = ASTNodeOperator.getASTNodeBodyStatementList(nodeTar);

		// TODO get statement mapping
		GlobalLog.LogProcess(GlobalProperty.MAPPINGSTATEMENT);
		List<ASTNodeMappingElement> mappingList = StatementOperator.getMappingList(stListRef,stListTar);

		// TODO get token mapping relationship
		GlobalLog.LogProcess(GlobalProperty.BUILDINGIDRTOKINMPAS);
		Map<String,List> tokenMap = new HashMap<>();
		Map<String,List<ASTNodeMappingElement>> nodeMap = new HashMap<>();
		buildMapping(tokenMap,nodeMap,mappingList);
		
		// TODO check token maps
		GlobalLog.LogProcess(GlobalProperty.CHECKINGIDRINCONSISTENtBUGS);
		IDRReportElement reportElement = checkMap(tokenMap,nodeMap);

		if(reportElement != null){
			result = GenerateBugReport.generateBugReport(GlobalProperty.IDRBug,
					nodeRef.toString(), nodeTar.toString(), reportElement.toString());
		}
		return result;
	}

	/**
	 * TODO design IDR details
	 * check whether there is a token in ref maps several tokens in tar
	 * NOTE: if more than 2 unmatched, it may be intentional by the developer  
	 * @param tokenMap
	 * @param nodeMap 
	 * @return
	 */
	private static IDRReportElement checkMap(Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodeMap) {		
		List<IDRReportElement> list = new ArrayList<IDRReportElement>();
		for(String key: tokenMap.keySet()){
			if(tokenMap.get(key).size() >= 2 && tokenMap.get(key).size()<=3){
				IDRReportElement ele = new IDRReportElement();
				ele.ref = key;
				ele.tarList = tokenMap.get(key);
				ele.nodeList = nodeMap.get(key);
				list.add(ele);
			}
		}
		if(list.size() == 1)
			return list.get(0);
		return null;
	}

	/**
	 * TODO generate token map
	 * Token(ref) : token1,token2(tar)
	 * @param tokenMap
	 * @param nodeMap 
	 * @param mappingList
	 */
	private static void buildMapping(Map<String, List> tokenMap,
			Map<String, List<ASTNodeMappingElement>> nodeMap, List<ASTNodeMappingElement> mappingList) {
		ASTNodeMappingElement e;
		for(int index=0;index<mappingList.size();index++){
			e = mappingList.get(index);
			ASTNodeOperator.getASTNodeTokenMapping(tokenMap,nodeMap, e);
		}
		
	}
}
