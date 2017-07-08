package com.song.javatests.demo1.socket.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ExpressionClient {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Scanner scan = new Scanner(System.in); 
		scan.useDelimiter("\n");
		System.out.println("add connect ...");
		try {
			socket = new Socket("127.0.0.1", 8088);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("connected !");
		System.out.println("请输入表达式和赋值语句（用逗号隔开）：");
		while (scan.hasNext()) {
			String input = scan.next();
			System.out.println(input);
			try {
				writer.write(input);
				writer.flush();
				String result = reader.readLine();
				System.out.println(result);
				System.out.println("请输入表达式和赋值语句（用逗号隔开）：");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
