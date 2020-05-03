package com.codurance.string_calculator;

import java.util.regex.Pattern;

import static java.util.Arrays.stream;

public class StringCalculator {
    public int add(String input) {
        if (input.isEmpty()){
            return 0;
        }

        String separators = ",\n";

        if(input.startsWith("//")){
            separators += input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }

        System.out.println(separators);

        return stream(input.split("["+ separators +"]"))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
