package com.codurance.string_calculator;

import static java.util.Arrays.stream;

public class StringCalculator {
    public int add(String input) {
        if (input.isEmpty()){
            return 0;
        }

        return stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
