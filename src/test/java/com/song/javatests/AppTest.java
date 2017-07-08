package com.song.javatests;

import java.util.regex.Pattern;

import org.junit.Test;

import com.song.javatests.demo1.expression.Expression;
import com.song.javatests.demo1.expression.composite.AndCompositeExpression;
import com.song.javatests.demo1.expression.composite.InvertCompositeExpression;
import com.song.javatests.demo1.expression.composite.OrCompositeExpression;
import com.song.javatests.demo1.expression.simple.EqualExpression;
import com.song.javatests.demo1.expression.simple.LessOrEqualExpression;
import com.song.javatests.demo1.expression.simple.LessThanExpression;
import com.song.javatests.demo1.expression.simple.MoreOrEqualExpression;
import com.song.javatests.demo1.expression.simple.MoreThanExpression;
import com.song.javatests.demo1.expression.simple.NotEqualExpression;
import com.song.javatests.demo1.util.ExpressionType;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void test1() {
		String test = "a>1&&(b<5||c>6)&&(!(a<b))";
		String test2 = "1<2||6>4&&6==2";
		String simple = "1<3";
		Expression e = createExpression(simple);
		System.out.println(e.getVlaue());
		Expression e2 = createExpression(test2);
		System.out.println(e2.getVlaue());
		/*String[] strs = test.split("\\((.+)\\)");
		for (String string : strs) {
			System.out.println(string);
		}*/
	}
	public Expression createExpression(String expressionStr) {
		String str = expressionStr;
		System.out.println("str :" + str);
		int index = 0; 
		int temp = 0;
		if (str.contains(")")) {
			
		}
		
		if (str.contains(ExpressionType.AND) || str.contains(ExpressionType.OR)
				|| str.contains(ExpressionType.INVERT)) {
			String operator = searchLastOperator(str);
			System.out.println("op is :" + operator);
			String[] exps = str.split(operator);
			if (operator.equals(ExpressionType.AND)) {
				return new AndCompositeExpression(createExpression(exps[0]), createExpression(exps[1]));
			}
			if (operator.equals(ExpressionType.OR)) {
				exps = str.split(operator.replaceAll("\\|", "\\\\|"));
				return new OrCompositeExpression(createExpression(exps[0]), createExpression(exps[1]));
			}
			if (operator.equals(ExpressionType.INVERT)) {
				return new InvertCompositeExpression(createExpression(exps[1]));
			}
			return null;
		}else {
			if (str.contains(ExpressionType.MORETHAN)) {
				String[] numbers = str.split(ExpressionType.MORETHAN);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new MoreThanExpression(left, right);
			}
			if (str.contains(ExpressionType.MOREOREQUAL)) {
				String[] numbers = str.split(ExpressionType.MOREOREQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new MoreOrEqualExpression(left, right);
			}
			if (str.contains(ExpressionType.LESSTHAN)) {
				String[] numbers = str.split(ExpressionType.LESSTHAN);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new LessThanExpression(left, right);
			}
			if (str.contains(ExpressionType.LESSOREQUAL)) {
				String[] numbers = str.split(ExpressionType.LESSOREQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new LessOrEqualExpression(left, right);
			}
			if (str.contains(ExpressionType.EQUAL)) {
				String[] numbers = str.split(ExpressionType.EQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new EqualExpression(left, right);
			}
			if (str.contains(ExpressionType.NOTEQUAL)) {
				String[] numbers = str.split(ExpressionType.NOTEQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new NotEqualExpression(left, right);
			}
			return null;
		}
	}
	public String searchLastOperator(String expressionStr) {
		int andIndex = expressionStr.lastIndexOf(ExpressionType.AND);
		int orIndex = expressionStr.lastIndexOf(ExpressionType.OR);
		int invertIndex = expressionStr.lastIndexOf(ExpressionType.INVERT);
		if (andIndex > orIndex) {
			if (andIndex > invertIndex) {
				return ExpressionType.AND;
			}else {
				return ExpressionType.INVERT;
			}
		}else if (orIndex > invertIndex) {
			return ExpressionType.OR;
		}else {
			return ExpressionType.INVERT;
		}
	}
    
}
