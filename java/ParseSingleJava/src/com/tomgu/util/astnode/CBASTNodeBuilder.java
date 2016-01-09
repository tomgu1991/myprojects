package com.tomgu.util.astnode;

import org.eclipse.jdt.core.dom.*;

import com.tomgu.entity.astnode.*;
import com.tomgu.entity.astnode.expression.*;
import com.tomgu.entity.astnode.statement.*;
import com.tomgu.entity.astnode.statement.variabledeclaration.*;
import com.tomgu.property.GlobalProperty;

public class CBASTNodeBuilder {
	/**
	 * build statement
	 * @param node
	 * @return
	 */
	private static CBStatement buildCBStatement(Statement node){
		CBStatement result;
		switch(node.getNodeType()){
		case ASTNode.ASSERT_STATEMENT:
			result = new CBAssertStatement((AssertStatement) node);
			break;
		case ASTNode.BLOCK:
			result = new CBBlock((Block) node);
			break;
		case ASTNode.BREAK_STATEMENT:
			result = new CBBreakStatement((BreakStatement)node);
			break;
		case ASTNode.CONSTRUCTOR_INVOCATION:
			result = new CBConstructorInvocation((ConstructorInvocation)node);
			break;
		case ASTNode.CONTINUE_STATEMENT:
			result = new CBContinueStatemment((ContinueStatement) node);
			break;
		case ASTNode.DO_STATEMENT:
			result = new CBDoStatement((DoStatement)node);
			break;
		case ASTNode.EMPTY_STATEMENT:
			result = new CBEmptyStatement((EmptyStatement) node);
			break;
		case ASTNode.ENHANCED_FOR_STATEMENT:
			result = new CBEnhancedForStatement((EnhancedForStatement) node);
			break;
		case ASTNode.EXPRESSION_STATEMENT:
			result = new CBExpressionStatement((ExpressionStatement)node);
			break;
		case ASTNode.FOR_STATEMENT:
			result = new CBForStatement((ForStatement)node);
			break;
		case ASTNode.IF_STATEMENT:
			result = new CBIfStatement((IfStatement) node);
			break;
		case ASTNode.LABELED_STATEMENT:
			result = new CBLabeledStatement((LabeledStatement)node);
			break;
		case ASTNode.RETURN_STATEMENT:
			result = new CBReturnStatement((ReturnStatement) node);
			break;
		case ASTNode.SUPER_CONSTRUCTOR_INVOCATION:
			result = new CBSuperConstrutorInvocation((SuperConstructorInvocation) node);
			break;
		case ASTNode.SWITCH_CASE:
			result = new CBSwitchCase((SwitchCase) node);
			break;
		case ASTNode.SWITCH_STATEMENT:
			result = new CBSwitchStatement((SwitchStatement) node);
			break;
		case ASTNode.SYNCHRONIZED_STATEMENT:
			result = new CBSychronizedStatement((SynchronizedStatement) node);
			break;
		case ASTNode.THROW_STATEMENT:
			result = new CBThrowStatement((ThrowStatement) node);
			break;
		case ASTNode.TRY_STATEMENT:
			result = new CBTryStatement((TryStatement) node);
			break;
		case ASTNode.VARIABLE_DECLARATION_STATEMENT:
			result = new CBVariableDeclarationStatement((VariableDeclarationStatement) node);
			break;
		case ASTNode.WHILE_STATEMENT:
			result = new CBWhileStatement((WhileStatement) node);
			break;
		default:
			result = new CBStatement(node);
		}
		return result;
	}
	
	private static CBExpression buildCBExpression(Expression node){
		CBExpression result;
		switch(node.getNodeType()){
		case Expression.ARRAY_ACCESS:
			result = new CBArrayAccess((ArrayAccess)node);
		case Expression.ASSIGNMENT:
			result = new CBAssignment((Assignment)node);
			break;
		case ExpressionStatement.BOOLEAN_LITERAL:
			result = new CBBooleanLiteral((BooleanLiteral)node);
			break;
		case Expression.CAST_EXPRESSION:
			result = new CBCastExpression((CastExpression)node);
			break;
		case Expression.CHARACTER_LITERAL:
			result = new CBCharacterLiteral((CharacterLiteral)node);
			break;
		case Expression.CONDITIONAL_EXPRESSION:
			result = new CBConditionalExpression((ConditionalExpression)node);
			break;
		case Expression.FIELD_ACCESS:
			result = new CBFieldAccess((FieldAccess)node);
			break;
		case Expression.INFIX_EXPRESSION:
			result = new CBInfixExpression((InfixExpression)node);
			break;
		case Expression.INSTANCEOF_EXPRESSION:
			result = new CBInstanceofExpression((InstanceofExpression)node);
			break;
		case Expression.METHOD_INVOCATION:
			result = new CBMethodInvocation((MethodInvocation)node);
			break;
		case Expression.QUALIFIED_NAME:
		case Expression.SIMPLE_NAME:
			result = new CBName((Name) node);
			break;
		case Expression.NULL_LITERAL:
			result = new CBNullLiteral((NullLiteral) node);
			break;
		case Expression.NUMBER_LITERAL:
			result = new CBNumberLiteral( (NumberLiteral) node);
			break;
		case Expression.PARENTHESIZED_EXPRESSION:
			result = new CBParenthesizedExpression((ParenthesizedExpression)node);
			break;
		case Expression.POSTFIX_EXPRESSION:
			result = new CBPostfixExpression((PostfixExpression) node);
			break;
		case Expression.PREFIX_EXPRESSION:
			result = new CBPrefixExpression((PrefixExpression) node);
			break;
		case Expression.STRING_LITERAL:
			result = new CBStringLiteral((StringLiteral) node);
			break;
		case Expression.SUPER_FIELD_ACCESS:
			result = new CBSuperFieldAccess((SuperFieldAccess) node);
			break;
		case Expression.SUPER_METHOD_INVOCATION:
			result = new CBSuperMethodInvocation((SuperMethodInvocation) node);
			break;
		case Expression.VARIABLE_DECLARATION_EXPRESSION:
			result = new CBVariableDeclarationExpression((VariableDeclarationExpression) node);
			break;
		default:
			result = new CBExpression(node);
		}
		return result;
		
	}
	
	private static CBASTNode buildCBANode(ASTNode node){
		CBASTNode result;
		switch(node.getNodeType()){
		case ASTNode.VARIABLE_DECLARATION_FRAGMENT:
			result = new CBVariableDeclaratrionFragment((VariableDeclarationFragment)node);
			break;
		case ASTNode.SINGLE_VARIABLE_DECLARATION:
			result = new CBSingleVariableDeclaration((SingleVariableDeclaration)node);
			break;
		case ASTNode.CATCH_CLAUSE:
			result = new CBCatchClause((CatchClause) node);
			break;
		case ASTNode.FIELD_DECLARATION:
			result = new CBFiledDeclaration((FieldDeclaration) node);
			break;
		case ASTNode.INITIALIZER:
			result = new CBInitializer((Initializer) node);
			break;
		default:
			result = new CBASTNode(node);
		}
		return result;
	}
	
	/**
	 * get ASTNode type to build CBASTNode
	 * 0 ASTNode
	 * 1 Statement
	 * 2 Expression
	 * @param node
	 * @return
	 */
	private static int getASTNodeType(ASTNode node){
		int result;
		if(node instanceof Statement){
			result = GlobalProperty.CBStatmentType;
		}else if (node instanceof Expression){
			result = GlobalProperty.CBExpressionType;
		}else
			result = GlobalProperty.CBASTNodeType;
		return result;
	}

	/**
	 * build CBASTNode by node Type
	 * @param node
	 * @return
	 */
	public static AbstractCBASTNode build(ASTNode node) {
		AbstractCBASTNode result;
		switch (getASTNodeType(node)) {
		case GlobalProperty.CBStatmentType:
			result = buildCBStatement((Statement) node);
			break;
		case GlobalProperty.CBExpressionType:
			result = buildCBExpression((Expression) node);
			break;
		default:
			result = buildCBANode(node);
			break;
		}
		return result;
	}
}
