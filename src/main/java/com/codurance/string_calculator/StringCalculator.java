package com.codurance.string_calculator;

public class StringCalculator {
    public int add(String input) {
        if (input.isEmpty()){
            return 0;
        }

        String[] numbers = input.split(",");

        int result = 0;

        for (String num: numbers) {
            result += Integer.parseInt(String.valueOf(num));
        }
        return result;
    }
}
