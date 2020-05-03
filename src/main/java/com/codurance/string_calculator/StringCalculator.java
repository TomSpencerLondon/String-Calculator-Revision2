package com.codurance.string_calculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String input) throws MinusNumberNotAllowedException {
        if (input.isEmpty()){
            return 0;
        }

        String separators = ",\n";

        if(input.startsWith("//")){
            separators += input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }

        System.out.println(separators);

        int result = 0;
        List<String> negatives = new ArrayList<>();

        for (String number: input.split("[" + separators + "]")) {
            if(Integer.parseInt(number) > 0){
                result += Integer.parseInt(number);
            }else{
                negatives.add(number);
            }
        }

        if(negatives.isEmpty()){
            return result;
        }else{
            throw new MinusNumberNotAllowedException(negatives);
        }
    }

    static class MinusNumberNotAllowedException extends Exception {
        public MinusNumberNotAllowedException(List<String> negatives) {
            super("negatives not allowed: " + String.join(" ", negatives));
        }
    }
}
