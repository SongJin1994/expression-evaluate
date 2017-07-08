package com.song.javatests.demo1.expression.simple;


public class EqualExpression extends SimpleExpression {
	
	public EqualExpression(int l, int r) {
		super(l, r);
	}

	@Override
	public boolean getVlaue() {
		return this.getLeft() == this.getRight();
	}

}
