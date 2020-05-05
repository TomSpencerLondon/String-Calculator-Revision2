package com.codurance.string_calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String input) throws MinusNumberNotAllowedException {
        if (input.isEmpty()){
            return 0;
        }

        String[] numbers;

        if (input.contains("//")){
            String sanitizedInput = replaceCustomSeparator(input);
            numbers = getNumbers(sanitizedInput);
        } else {
            numbers = getNumbers(input);
        }

        checkForNegatives(numbers);

        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .filter(n -> n <= 1000)
                .sum();
    }

    public String replaceCustomSeparator(String input) {
        String numbers = input.split("\n")[1];
        String customSeparator;

        if(input.contains("[")){
            customSeparator = input.substring(input.indexOf('[') + 1, input.indexOf(']'));
        }else{
            customSeparator = input.substring(2, input.indexOf("\n"));
        }
        String result = numbers.replace(customSeparator, ",");

        return result;
    }

    private String[] getNumbers(String input) {
        return input.split("[,\n]");
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
