package com.codurance.string_calculator;

public class StringCalculator {
    public int add(String input) {
        if (input.isEmpty()){
            return 0;
        }

        return Integer.parseInt(String.valueOf(input.charAt(0)));
    }
}
