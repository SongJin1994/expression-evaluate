package com.song.javatests.demo1.expression.composite;

import com.song.javatests.demo1.expression.Expression;

public abstract class CompositeExpression extends Expression {
	private Expression leftExpression;
	private Expression rightExpression; 
	
	public CompositeExpression() {
		super();
	}
	
	public Expression getLeftExpression() {
		return leftExpression;
	}

	public void setLeftExpression(Expression leftExpression) {
		this.leftExpression = leftExpression;
	}

	public Expression getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}

	public CompositeExpression(Expression l, Expression r) {
		super();
		this.leftExpression = l;
		this.rightExpression = r;
	}
	

}
