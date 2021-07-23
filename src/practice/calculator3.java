package practice;

import java.util.Scanner;

public class calculator3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("??Jy?}?l ??J0????");
		String start = scan.next();

		// ?`?M
		double total = 0;

		while (!start.equals("0")) {
			String userInput = scan.next();
			// ??J0?N????
			if (userInput.equals("0")) {
				break;
			}
			// ???o?B??????m userInput.indexOf("+")???+???????
			int userInputIndex = 0;
			if (userInput.contains("+")) {
				userInputIndex = userInput.indexOf("+");
			} else if (userInput.contains("-")) {
				userInputIndex = userInput.indexOf("-");
			} else if (userInput.contains("*")) {
				userInputIndex = userInput.indexOf("*");
			} else if (userInput.contains("/")) {
				userInputIndex = userInput.indexOf("/");
			}

			// ???B?????e??????
			// number?O?}?l??B??????????"?r??"?????
			double number = 0;
			if (userInputIndex != 0) {
				number = Double.parseDouble(userInput.substring(0, userInputIndex));

			}
			// ?B????
			String operation = userInput.substring(userInputIndex, userInputIndex + 1);

			int longe = userInput.length();
			// ??G???
			double number2 = Double.parseDouble(userInput.substring(userInputIndex + 1, longe));
			// ?}?l?p??
			if (number != 0) {
				// ??@???i
				total = Count(number, number2, operation);
			} else {
				// ??G??????
				switch (operation) {
					case "+":
						total = total + number2;
						break;
					case "-":
						total = total - number2;
						break;

					case "*":
						total = total * number2;
						break;

					case "/":
						total = total / number2;
						break;
				}
			}
			double answer = (double) (Math.round(total * 100)) / 100;
			System.out.println(answer);
		}
	}

	public static double Count(double number, double number2, String operation) {
		double count = 0;
		switch (operation) {
			case "+":
				count = number + number2;
				break;
			case "-":
				count = number - number2;
				break;

			case "*":
				count = number * number2;
				break;

			case "/":
				count = number / number2;
				break;
		}

		return count;
	}
}
