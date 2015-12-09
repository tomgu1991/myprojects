package com.tomgu.util.astnode.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Statement;

import com.tomgu.entity.StatementMappingElement;
import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.property.GlobalProperty;
import com.tomgu.util.astnode.CBASTNodeBuilder;
import com.tomgu.util.string.StringUtil;

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
			List<ASTNode> stListRef, List<ASTNode> stListTar) {
		List<StatementMappingElement> list = new ArrayList<StatementMappingElement>();
		
		//calculate similarity
		double[][] similarity = new double[stListRef.size()][stListTar.size()];
		double minDistance;
		for(int indexRef = 0;indexRef<stListRef.size();indexRef++){
			for(int indexTar=0;indexTar<stListTar.size();indexTar++){
				minDistance = (double)StringUtil.minDistance(stListRef.get(indexRef).toString()
						, stListTar.get(indexTar).toString());
				similarity[indexRef][indexTar] = 1.0 - (minDistance/(stListRef.get(indexRef).toString().length()
						+ stListTar.get(indexTar).toString().length()));
			}
			
		}
		
		// mapping
		boolean[] available = new boolean[stListTar.size()];
		for(int index = 0;index<available.length;index++){
			available[index] = true;
		}
		int[] mapResult = new int[stListRef.size()];
		double tem = 0.0;
		for(int indexRef = 0;indexRef<stListRef.size();indexRef++){
			mapResult[indexRef] = -1;
			tem = 0.0;
			for(int indexTar=0;indexTar<stListTar.size();indexTar++){
				if(similarity[indexRef][indexTar] > tem && 
						similarity[indexRef][indexTar]>GlobalProperty.SIMILARITY
						&& available[indexTar]){
					tem = similarity[indexRef][indexTar];
					mapResult[indexRef] = indexTar;
					available[indexTar] = false;
				}
			}
		}
		
		// build StatmentMappingElement
		StatementMappingElement element;
		for(int indexMap=0;indexMap<mapResult.length;indexMap++){
			if(mapResult[indexMap] >= 0){
				element = new StatementMappingElement(
						stListRef.get(indexMap),
						stListTar.get(mapResult[indexMap]));
				list.add(element);
			}
		}
		return list;
	}

	/**
	 * mapping clone node pair tokens
	 * @param map
	 * @param e
	 */
	public static void getStatementTokenMapping(Map<String, List> map,
			StatementMappingElement e){
		ASTNode ref = e.getRef();
		ASTNode tar = e.getTar();
		AbstractCBASTNode refCBASTNode = CBASTNodeBuilder.buildCBASTNode(ref);
		AbstractCBASTNode tarCBASTNode = CBASTNodeBuilder.buildCBASTNode(tar);
		refCBASTNode.mapTokens(tarCBASTNode, map);
		
	}
}
