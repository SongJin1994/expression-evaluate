package com.song.javatests.demo1.expression.composite;

import com.song.javatests.demo1.expression.Expression;

public class OrCompositeExpression extends CompositeExpression {

	public OrCompositeExpression(Expression l, Expression r) {
		super(l, r);
	}

	@Override
	public boolean getVlaue() {
		return this.getLeftExpression().getVlaue() || this.getRightExpression().getVlaue();
	}

}
