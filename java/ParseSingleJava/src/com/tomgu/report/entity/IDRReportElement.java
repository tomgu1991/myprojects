package com.tomgu.report.entity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

/**
 * TODO IDR report element, record refStatement and tarStatement and potential error
 * @author guzuxing
 *
 */
public class IDRReportElement {
	public String ref;
	public List<String> tarList = new ArrayList<>();
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(ref+" -> ");
		for(String s: tarList){
			buffer.append(s+", ");
		}
		return buffer.toString();
	}
	
	
}
