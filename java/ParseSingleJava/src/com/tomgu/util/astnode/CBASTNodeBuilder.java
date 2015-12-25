package com.tomgu.util.astnode;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import com.tomgu.entity.astnode.AbstractCBASTNode;
import com.tomgu.entity.astnode.CBASTNode;
import com.tomgu.entity.astnode.expression.CBArrayAccess;
import com.tomgu.entity.astnode.expression.CBAssignment;
import com.tomgu.entity.astnode.expression.CBBooleanLiteral;
import com.tomgu.entity.astnode.expression.CBCastExpression;
import com.tomgu.entity.astnode.expression.CBCharacterLiteral;
import com.tomgu.entity.astnode.expression.CBConditionalExpression;
import com.tomgu.entity.astnode.expression.CBExpression;
import com.tomgu.entity.astnode.expression.CBFieldAccess;
import com.tomgu.entity.astnode.expression.CBInfixExpression;
import com.tomgu.entity.astnode.expression.CBInstanceofExpression;
import com.tomgu.entity.astnode.expression.CBMethodInvocation;
import com.tomgu.entity.astnode.expression.CBName;
import com.tomgu.entity.astnode.expression.CBNullLiteral;
import com.tomgu.entity.astnode.expression.CBNumberLiteral;
import com.tomgu.entity.astnode.expression.CBParenthesizedExpression;
import com.tomgu.entity.astnode.expression.CBPostfixExpression;
import com.tomgu.entity.astnode.expression.CBPrefixExpression;
import com.tomgu.entity.astnode.expression.CBStringLiteral;
import com.tomgu.entity.astnode.expression.CBSuperFieldAccess;
import com.tomgu.entity.astnode.expression.CBSuperMethodInvocation;
import com.tomgu.entity.astnode.expression.CBVariableDeclarationExpression;
import com.tomgu.entity.astnode.statement.CBExpressionStatement;
import com.tomgu.entity.astnode.statement.CBStatement;
import com.tomgu.entity.astnode.statement.CBVariableDeclarationStatement;
import com.tomgu.property.GlobalProperty;
import com.tomgu.util.MapUtil;

public class CBASTNodeBuilder {
	/**
	 * build statement
	 * @param node
	 * @return
	 */
	private static CBStatement buildCBStatement(Statement node){
		CBStatement result;
		switch(node.getNodeType()){
		case ASTNode.VARIABLE_DECLARATION_STATEMENT:
			result = new CBVariableDeclarationStatement((VariableDeclarationStatement) node);
			break;
		case ASTNode.EXPRESSION_STATEMENT:
			result = new CBExpressionStatement((ExpressionStatement)node);
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
