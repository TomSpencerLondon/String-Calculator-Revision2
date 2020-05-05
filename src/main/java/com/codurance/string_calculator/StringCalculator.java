package com.codurance.string_calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String input) throws MinusNumberNotAllowedException {
        if (input.isEmpty()){
            return 0;
        }

        String separators = getSeparatorsFrom(input);
        String[] numbers = getNumbers(input, separators);
        checkForNegatives(numbers);

        return Arrays.stream(numbers)
                .filter(n -> Integer.parseInt(n) < 1000)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private String[] getNumbers(String input, String separators) {
        String input1 = input;
        if(input1.startsWith("//")){
            input1 = input1.substring(input1.indexOf("\n") + 1);
        }

        return input1.split("[" + separators + "]");
    }

    private String getSeparatorsFrom(String input) {
        String separators = ",\n";

        if(input.startsWith("//")){
            separators += input.substring(2, input.indexOf("\n"));
        }
        return separators;
    }

    private void checkForNegatives(String[] numbers) throws MinusNumberNotAllowedException {
        List<String> negatives = Arrays.stream(numbers)
                .filter(n -> Integer.parseInt(n) < 0)
                .collect(Collectors.toList());

        if(!negatives.isEmpty()){
            throw new MinusNumberNotAllowedException(negatives);
        }
    }

    static class MinusNumberNotAllowedException extends Exception {
        public MinusNumberNotAllowedException(List<String> negatives) {
            super("negatives not allowed: " + String.join(" ", negatives));
        }
    }
}
