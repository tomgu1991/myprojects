package com.tomgu.report.entity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import com.tomgu.entity.ASTNodeMappingElement;
import com.tomgu.property.GlobalProperty;

/**
 * TODO IDR report element, record refStatement and tarStatement and potential error
 * @author guzuxing
 *
 */
public class IDRReportElement {
	public String ref;
	public List<String> tarList = new ArrayList<>();
	public List<ASTNodeMappingElement> nodeList = new ArrayList<ASTNodeMappingElement>();
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(ref+" -> ");
		for(String s: tarList){
			buffer.append(s+", ");
		}
		buffer.append("\n");
		for(int index = 0;index<nodeList.size();index++){
			buffer.append(nodeList.get(index).getRef().toString());
			buffer.append(GlobalProperty.SUBSEPARATOR+"\n");
			buffer.append(nodeList.get(index).getTar().toString()+"\n");
		}
		return buffer.toString();
	}
	
	
}
