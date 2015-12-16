package com.tomgu.property;

public interface GlobalProperty {
	/**
	 * get ASTNode type to build CBASTNode
	 * 0 ASTNode
	 * 1 Statement
	 * 2 Expression
	 */
	public final int CBASTNodeType = 0;
	public final int CBStatmentType = 1;
	public final int CBExpressionType = 2;
	
	/**
	 * for ASTNode similarity
	 */
	public final double SIMILARITY = 0.75;
	
	/**
	 * for log
	 */
	public final String IDRBug = "Inconsistent Direct Renaming Bug";
	public final String GETCLONEPAIR = "Get Clone Pair from Clone Detection Result Set";
	public final String GETSMALLESTASTNODE = "Get Clone code fragment smallest ASTNode";
	public final String DETECTINGIDRBUG = "Detecting Inconsistent Direct Renaming Bug";
	public final String REPORTDETECTIONRESULT = "Report clone code bug detection result";
	public final String SEPARATOR = "********************";




}

