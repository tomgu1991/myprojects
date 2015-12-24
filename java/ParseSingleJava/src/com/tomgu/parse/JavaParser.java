package com.tomgu.parse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.NodeFinder;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import com.tomgu.util.FileReaderUtil;
import com.tomgu.util.log.ParseLog;

public class JavaParser {
	private ASTParser parser;

	public JavaParser(){
		parser = ASTParser.newParser(AST.JLS8);
	}

	
	/**
	 * parse source code and return compilutionUnit for further analysis
	 * @param codeStr
	 * @return
	 */
	public CompilationUnit parseCompilationUnit(String filePath){
		String codeStr = FileReaderUtil.readFileToStringByChar(filePath);
		parser.setSource(codeStr.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		
		return cu;
	}

	/**
	 * find ASTNode covering lines in java source file
	 * @param codeStr source code
	 * @param lineNumber target code fragments line number
	 */
	public void findASTNodeByLine(CompilationUnit cu, int lineNumber){
		//		addASTVisitorToCU(cu);
		List list = cu.types();
		TypeDeclaration typeNode = (TypeDeclaration) list.get(0);
		List<BodyDeclaration> bodyNodeList = typeNode.bodyDeclarations();
		BodyDeclaration bodyDeclarationNode;
		int startPosition,endPosition;
		int startLine,endLine;
		ASTNode nodeFound = null;
		for(int i=0;i<bodyNodeList.size();i++){
			bodyDeclarationNode = bodyNodeList.get(i);
			startPosition = bodyDeclarationNode.getStartPosition();
			endPosition = startPosition + bodyDeclarationNode.getLength()-1;
			startLine = cu.getLineNumber(startPosition);
			endLine = cu.getLineNumber(endPosition);
			if(startLine <= lineNumber && endLine >= lineNumber){
				nodeFound = BodyDeclaration.copySubtree(cu.getAST(), bodyDeclarationNode);
				System.out.println(startPosition);
				break;
			}
		}
		System.out.println(nodeFound.toString());
		//input position using NodeFinder
		NodeFinder finder = new NodeFinder(cu.getRoot(), 7308, 438);
		ASTNode result = finder.getCoveringNode();
		System.out.println(result.toString());
	}
	
	/**
	 * find the min ASTNode covering the [position,position+length] source code fragment
	 * @param codeStr
	 * @param position
	 * @param length
	 * @return
	 */
	public ASTNode getMinASTNodeByPosition(CompilationUnit cu, int position, int length){
		ASTNode result = null;
		NodeFinder finder = new NodeFinder(cu.getRoot(), position, length);
		result = finder.getCoveringNode();
		return result;
	}
	
	/**
	 * return line covered minist astnode in filepath
	 * @param filePath
	 * @param cu
	 * @param line
	 * @return astnode
	 */
	public ASTNode getMinCoveredASTNode(String filePath, CompilationUnit cu,int line){
		int position = FileReaderUtil.getPositionByLineNumber(filePath, line);
		int length = FileReaderUtil.getLineLength(filePath, line);
		NodeFinder finder = new NodeFinder(cu.getRoot(), position, length);
		ASTNode result = finder.getCoveredNode();
		return result;	
	}
	
	/**
	 * add visitor to compilationUnit
	 * @param cu
	 */
	private void addASTVisitorToCU(CompilationUnit cu) {
		cu.accept(new ASTVisitor(){
			Set<String> names = new HashSet<>();

			public boolean visit(VariableDeclarationFragment node){
				SimpleName name = node.getName();
				this.names.add(name.getIdentifier());
				System.out.println("Declaration of '"+name+"' at line "+
						cu.getLineNumber(node.getStartPosition()));
				return true;
			}

			public boolean visit(SimpleName node) {
				if (this.names.contains(node.getIdentifier())) {
					System.out.println("Usage of '" + node + "' at line "
							+ cu.getLineNumber(node.getStartPosition()));
				}
				return true;
			}
		});

	}

	/**
	 * TODO return the most minimal ASTNode covering the largest lines in lines
	 * @param cuRef
	 * @param linesRef
	 * @return
	 */
	public ASTNode getMinASTNodeByLines(String filePath,CompilationUnit cu, int[] lines) {
		// TODO return the most minimal ASTNode covering all the lines
		
		// should compare each line's ASTNode and return the most used one
		// build nodeMap
		Map<ASTNode,Integer> nodeMap = new HashMap<>();
		int position,length;
		NodeFinder finder;
		ASTNode temNode;
		for(int i=0;i<lines.length;i++){
			position = FileReaderUtil.getPositionByLineNumber(filePath, lines[i]);
			length = FileReaderUtil.getLineLength(filePath, lines[i]);
			finder = new NodeFinder(cu.getRoot(),position,length);
			temNode = finder.getCoveringNode();
			if(nodeMap.containsKey(temNode)){
				nodeMap.put(temNode, nodeMap.get(temNode)+1);
			}else
				nodeMap.put(temNode, new Integer(1));
		}
		
		// get most used one
		int count = 0;
		ASTNode result = null;
		for(ASTNode node:nodeMap.keySet()){
			if(nodeMap.get(node) > count){ // choose the max used one
				count = nodeMap.get(node);
				result = node;
			}else if (nodeMap.get(node) == count){// count same choose the smallest one -> position backward -> position number larger
				if(result.getStartPosition() < node.getStartPosition()){
					count = nodeMap.get(node);
					result = node;					
				}
			}else
				continue;
		}
		
		ParseLog.LogGetMinASTNodeByLinesLog(filePath, lines, result);
		
		return result;
		
//		int position = FileReaderUtil.getPositionByLineNumber(filePath, lines[0]);
//		int length = FileReaderUtil.getLineLength(filePath, lines[0]);
//		NodeFinder finder = new NodeFinder(cu.getRoot(), position, length);
//		ASTNode result = finder.getCoveringNode();
//		return result;
	}
}
