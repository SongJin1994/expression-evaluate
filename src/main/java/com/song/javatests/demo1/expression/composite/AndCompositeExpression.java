package com.song.javatests.demo1.expression.composite;

import com.song.javatests.demo1.expression.Expression;

public class AndCompositeExpression extends CompositeExpression {

	public AndCompositeExpression(Expression l, Expression r) {
		super(l, r);
	}

	@Override
	public boolean getVlaue() {
		return this.getLeftExpression().getVlaue() && this.getRightExpression().getVlaue();
	}

}
