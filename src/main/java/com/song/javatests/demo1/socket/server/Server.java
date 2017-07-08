package com.song.javatests.demo1.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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

public class Server implements Runnable{
	private Socket socket;
	public Server(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader reader = null;;
		BufferedWriter writer = null;
		String input = "";
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				input = reader.readLine();
				// 初始化input
				String[] temp = input.split(",");
				String expressionStr = temp[0];
				String[][] values = new String[temp.length-1][2];
				for (int i = 1; i < temp.length; i++) {
					values[i-1] = temp[i].split("=", 2);
				}
				for (String[] str : values) {
					expressionStr = expressionStr.replaceAll(str[0], str[1]);
				}
				System.out.println("in :" + input);
				boolean result = createExpression(expressionStr).getVlaue();
				System.out.println("out: " + result);
				writer.write("this result is: " + result + "\n");
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
	    }
		
	}
	public Expression createExpression(String expressionStr) {
		String str = expressionStr;
		System.out.println("the input is :" + str);
		//首尾有无括号判断
		if (str.startsWith("(")) {
			int index = 1;
			int flag = 0;
			while (index < str.length()) {
				if (str.charAt(index) == '(') {
					flag++;
				}
				if (str.charAt(index) == ')') {
					if (flag == 0) {
						str = str.substring(1, index) + str.substring(index + 1);
						break;
					}else {
						flag--;
					}
				}
				index++;
				
			}
		}
		//待修改
		if (str.startsWith("!") && str.endsWith(")")) {
			int index = 2;
			int flag = 0;
			while (index < str.length() - 1) {
				if (str.charAt(index) == '(') {
					flag++;
				}
				if (str.charAt(index) == ')') {
					flag--;
					if (flag < 0) {
						break;
					}
				}
				index++;
				
			}
			if (flag == 0) {
				return new InvertCompositeExpression(createExpression(str.substring(1)));
			}
			
		}
		if (str.contains(ExpressionType.AND) || str.contains(ExpressionType.OR)) {
			int operatorIndex = searchLastOperator(str);
			if (operatorIndex == -1) {
				return null;
			}
			String operator = str.substring(operatorIndex, operatorIndex+2);
			System.out.println("op is :" + operator);
			String leftExp = str.substring(0, operatorIndex);
			String rightExp = str.substring(operatorIndex+2);
			if (operator.equals(ExpressionType.AND)) {
				return new AndCompositeExpression(createExpression(leftExp), createExpression(rightExp));
			}
			if (operator.equals(ExpressionType.OR)) {
				return new OrCompositeExpression(createExpression(leftExp), createExpression(rightExp));
			}
			return null;
		}else {
			if (str.startsWith("!")) {
				return new InvertCompositeExpression(createExpression(str.substring(1)));
			}
			if (str.contains(ExpressionType.MOREOREQUAL)) {
				String[] numbers = str.split(ExpressionType.MOREOREQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new MoreOrEqualExpression(left, right);
			}
			if (str.contains(ExpressionType.LESSOREQUAL)) {
				String[] numbers = str.split(ExpressionType.LESSOREQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new LessOrEqualExpression(left, right);
			}
			if (str.contains(ExpressionType.NOTEQUAL)) {
				String[] numbers = str.split(ExpressionType.NOTEQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new NotEqualExpression(left, right);
			}
			if (str.contains(ExpressionType.MORETHAN)) {
				String[] numbers = str.split(ExpressionType.MORETHAN);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new MoreThanExpression(left, right);
			}
			if (str.contains(ExpressionType.LESSTHAN)) {
				String[] numbers = str.split(ExpressionType.LESSTHAN);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new LessThanExpression(left, right);
			}
			if (str.contains(ExpressionType.EQUAL)) {
				String[] numbers = str.split(ExpressionType.EQUAL);
				int left = Integer.parseInt(numbers[0]);
				int right = Integer.parseInt(numbers[1]);
				return new EqualExpression(left, right);
			}
			
			return null;
		}
	}
	public int searchLastOperator(String expressionStr) {
		int flag = 0;
		int index = expressionStr.length()-1;
		while (index > 0) {
			if (expressionStr.charAt(index) == ')') {
				flag++;
			}
			if (expressionStr.charAt(index) == '(') {
				flag--;
			}
			if ((expressionStr.charAt(index) == '&' && expressionStr.charAt(index-1) == '&')
					||(expressionStr.charAt(index) == '|' && expressionStr.charAt(index-1) == '|')) {
				if (flag == 0) {
					return index-1;
				}
			}
			index--;
		}
		return -1;
	}

}
