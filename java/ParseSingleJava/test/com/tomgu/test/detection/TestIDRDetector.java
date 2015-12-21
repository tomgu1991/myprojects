package com.tomgu.test.detection;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.tomgu.detecion.IDRDetector;
import com.tomgu.parse.JavaParser;
import com.tomgu.property.GlobalProperty;
import com.tomgu.util.log.GlobalLog;

public class TestIDRDetector {

	public static void main(String[] args) {
		/*
		 * clone detection
		CloneClass	5589
		25007	VersionInsensitiveBugComparator.java	227	230
		25008	VersionInsensitiveBugComparator.java	218	221
		*/
		String filePathRef = "./source/bug.java";
		String filePathTar = "./source/bug.java";
		int[] linesRef = {218,219,220}; 
		int[] linesTar = {227,228,229};
		TestIDRDetector detector = new TestIDRDetector();
		detector.IDRDetector(filePathRef,linesRef,
				filePathTar,linesTar);	
	}
	
	public void IDRDetector(String ref, int[] refLines, 
			String tar, int[] tarLines){
		//TODO get clone node pair lines from clone deteciton result
		GlobalLog.LogProcess(GlobalProperty.GETCLONEPAIR);
		String filePathRef = ref;
		String filePathTar = tar;
		//if line contains } remove this line
		int[] linesRef = refLines; 
		int[] linesTar = tarLines;
		GlobalLog.LogClonePairInfo(filePathRef, filePathTar, linesRef, linesTar);
		

		//TODO get minest ASTNode covering largest lines in the fragments
		GlobalLog.LogProcess(GlobalProperty.GETSMALLESTASTNODE);
		JavaParser parser = new JavaParser();
		CompilationUnit cuRef = parser.parseCompilationUnit(filePathRef);
		CompilationUnit cuTar = parser.parseCompilationUnit(filePathTar);
		ASTNode nodeRef = parser.getMinASTNodeByLines(filePathRef, cuRef,linesRef);
		ASTNode nodeTar = parser.getMinASTNodeByLines(filePathTar, cuTar,linesTar);

		// TODO get detection result
		GlobalLog.LogProcess(GlobalProperty.DETECTINGIDRBUG);
		String result = IDRDetector.IDRDetection(nodeRef, nodeTar);
		
		//TODO print detection result
		if(result != null){
			GlobalLog.LogProcess(GlobalProperty.REPORTDETECTIONRESULT);
			GlobalLog.LogBugResult(result);	
		}
	}

}
