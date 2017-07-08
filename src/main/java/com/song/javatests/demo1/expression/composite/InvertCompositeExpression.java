package com.song.javatests.demo1.expression.composite;

import com.song.javatests.demo1.expression.Expression;

public class InvertCompositeExpression extends CompositeExpression {

	public InvertCompositeExpression(Expression e) {
		super();
		this.setRightExpression(e);
	}

	@Override
	public boolean getVlaue() {
		return !this.getRightExpression().getVlaue();
	}

}
