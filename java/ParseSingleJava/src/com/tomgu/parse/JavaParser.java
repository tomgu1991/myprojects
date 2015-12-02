package com.tomgu.parse;

import java.util.HashSet;
import java.util.List;
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

public class JavaParser {
	private ASTParser parser;

	public JavaParser(){
		parser = ASTParser.newParser(AST.JLS8);
	}

	public void parse(String codeStr,int lineNumber){
		parser.setSource(codeStr.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
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
				nodeFound = bodyDeclarationNode.copySubtree(cu.getAST(), bodyDeclarationNode);
				System.out.println(startPosition);
				break;
			}
		}
		System.out.println(nodeFound.getNodeType());
		NodeFinder finder = new NodeFinder(cu.getRoot(), 7308, 438);
		ASTNode result = finder.getCoveringNode();
		System.out.println(result.getStartPosition());
		System.out.println(result.getParent().getStartPosition());
	}

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
}
