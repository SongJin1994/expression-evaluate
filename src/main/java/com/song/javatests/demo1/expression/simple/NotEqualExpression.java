package com.song.javatests.demo1.expression.simple;

public class NotEqualExpression extends SimpleExpression {
	
	public NotEqualExpression(int l, int r) {
		super(l, r);
	}

	@Override
	public boolean getVlaue() {
		return this.getLeft() != this.getRight();
	}

}
