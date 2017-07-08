package com.song.javatests;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Pattern p = Pattern.compile("\\d{3}-\\d{8}");
		while (scan.hasNext()) {
			String text = scan.next();
			if (text.equals("exit")) {
				break;
			}
			Matcher m = p.matcher(text);
			if (m.find()) {
				System.out.println(m.group());
			}else {
				System.out.println("none");
			}
		}
		System.out.println("over");
		scan.close();
	}

}
