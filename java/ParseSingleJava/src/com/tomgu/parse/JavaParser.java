package com.tomgu.parse;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class JavaParser {
	private ASTParser parser;

	public JavaParser(){
		parser = ASTParser.newParser(AST.JLS8);
	}

	public void parse(String codeStr){
		parser.setSource(codeStr.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
//		addASTVisitorToCU(cu);
		ASTNode root = cu.getRoot();
		AST ast = cu.getAST();
		System.out.println(root.getAST().equals(ast));

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
