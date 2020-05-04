package com.codurance.string_calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String input) throws MinusNumberNotAllowedException {
        if (input.isEmpty()){
            return 0;
        }

        String[] numbers = getNumbersFrom(input);

        return Arrays.stream(numbers)
                .filter(n -> Integer.parseInt(n) < 1000)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private String[] getNumbersFrom(String input) throws MinusNumberNotAllowedException {
        String separators = ",\n";

        if(input.startsWith("//")){
            separators += input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }

        String[] numbers = splitInput(input, separators);
        checkForNegatives(numbers);
        return numbers;
    }

    private void checkForNegatives(String[] numbers) throws MinusNumberNotAllowedException {
        List<String> negatives = Arrays.stream(numbers)
                .filter(n -> Integer.parseInt(n) < 0)
                .collect(Collectors.toList());

        if(!negatives.isEmpty()){
            throw new MinusNumberNotAllowedException(negatives);
        }
    }

    private String[] splitInput(String input, String separators) {
        return input.split("[" + separators + "]");
    }

    static class MinusNumberNotAllowedException extends Exception {
        public MinusNumberNotAllowedException(List<String> negatives) {
            super("negatives not allowed: " + String.join(" ", negatives));
        }
    }
}
