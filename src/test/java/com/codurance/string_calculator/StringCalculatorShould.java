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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorShould {
    StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "''; 0",
            "4; 4",
            "5; 5",
            "1,2; 3",
            "12,3; 15"
    }, delimiter = ';')
    void return_number_for_input(String input, int output) {
        assertEquals(output, stringCalculator.add(input));
    }
}
