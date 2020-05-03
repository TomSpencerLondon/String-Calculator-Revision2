package com.codurance.string_calculator;

//Create a simple String calculator with a single method:
//
//class StringCalculator {
//    int Add(string numbers);
//}
//    The method can take 1 or 2 comma-separated numbers, and will return their sum.
//
//        The method returns 0 when passed the empty string.
//git
//        Example:
//
//        Add("") // 0
//        Add("4") // 4
//        Add("1,2") // 3

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorShould {
    @Test
    void return_0_for_empty_string() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void return_4_for_string_4() {
        StringCalculator stringCalculator = new StringCalculator();
    }
}
