package com.codurance.string_calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String input) throws MinusNumberNotAllowedException {
        if (input.isEmpty()){
            return 0;
        }

        String[] numbers = getNumbers(input);

        checkForNegatives(numbers);

        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .filter(n -> n <= 1000)
                .sum();
    }

    private String[] getNumbers(String input) {
        String[] numbers;

        if (input.contains("//")){
            String sanitizedInput = replaceCustomSeparator(input);
            numbers = sanitizedInput.split("[,\n]");
        } else {
            numbers = input.split("[,\n]");
        }
        return numbers;
    }

    public String replaceCustomSeparator(String input) {
        String numbers = input.split("\n")[1];
        String customSeparator;

        if (input.contains("][")) {
            String temporary = input.substring(input.indexOf('[') + 1, input.lastIndexOf(']'));
            String[] result = temporary.split(Pattern.quote("]["));
            for (String s: result) {
                numbers = numbers.replace(s, ",");
            }
            return numbers;
        }else if(input.contains("[")){
            customSeparator = input.substring(input.indexOf('[') + 1, input.indexOf(']'));
        }  else {
            customSeparator = input.substring(2, input.indexOf("\n"));
        }

        String result = numbers.replace(customSeparator, ",");

        return result;
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
