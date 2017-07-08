package com.song.javatests.demo1.expression.simple;

import com.song.javatests.demo1.expression.Expression;

public abstract class SimpleExpression extends Expression{
	private int left;
	private int right;
	
	public SimpleExpression(int l, int r) {
		this.left = l;
		this.right = r;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
	
}
