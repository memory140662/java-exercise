package com.demo.topic;

import java.util.Arrays;
import java.util.Scanner;

enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/")
    ;

    private String value;

    Operator(String value) {
        this.value = value;
    }

    static Operator transFromString(String str) {
        return Arrays.stream(values())
                .filter(o -> o.getValue().equals(str))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {
        return value;
    }
}

class MyComputer {

    static Number calc(Number num1, Number num2, Operator operator) {
        switch (operator) {
            case ADD:
                return num1.doubleValue() + num2.doubleValue();
            case SUBTRACT:
                return num1.doubleValue() - num2.doubleValue();
            case MULTIPLY:
                return num1.doubleValue() * num2.doubleValue();
            case DIVIDE:
                return num1.doubleValue() / num2.doubleValue();
        }
        throw new RuntimeException("不支持的操作符號。必須為 +, -, *, /");
    }

    static Number calc(String num1, String num2, String operatorString) throws NumberFormatException {

        Operator operator = Operator.transFromString(operatorString);

        if (operator == null) {
            throw new RuntimeException("操錯符號錯誤！必須為 +, -, *, /");
        }
        
        return calc(Double.parseDouble(num1), Double.parseDouble(num2), operator);
    }

}

public class Topic1 {

    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);

        for(;;) {
            System.out.print("請輸入(exit離開)：");
            input = scanner.nextLine();

            if ("EXIT".equalsIgnoreCase(input)) {
                break;
            }

            String[] strings = input.split(" ");
            if (strings.length != 3) {
                System.out.println("輸入格式錯誤！ex. 1 + 2");
                continue;
            }

            String num1 = strings[0];
            String num2 = strings[2];
            String operator = strings[1];

            try {
                Number result = MyComputer.calc(num1, num2, operator);
                System.out.println(String.format("計算結果 %s = %.2f", input, result));
            } catch (NumberFormatException e) {
                System.out.println("數字格式錯誤！");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("***************");
        }

        System.out.println("謝謝您的使用。");
    }
}
