package com.codurance.string_calculator;

// 1. Create a simple String calculator with a single method:
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
//
// 2. Allow the Add method to handle an unknown amount of numbers.
//
//        Example:
//
//        Add("1,2,3,4,5,6,7,8,9") // 45
//
// 3. Allow the Add method to recognise newlines as well as commas as separators. The two separator types can be used interchangeably.
//
//        NB: Focus on the happy path - since this is not production code, it's fine if the code crashes if it's given invalid input (e.g. "1,\n2").
//
//        Example:
//
//        Add("1\n2,3") // 6
//
// 4. Optionally support custom separators. To change separator, the beginning of the string will contain a separate line that looks like this: “//<separator>\n<numbers>”
//
//        Example:
//
//        Add("//;\n1;2") // 3
//
// 5. Calling Add with a negative number will throw an exception negatives not allowed, and the negative that was passed.
//
//        If there are multiple negatives, show all of them in the exception message.
//
//        Example:
//
//        Add("1,-2,-3") // error: negatives not allowed: -2 -3

// 6. Ignore numbers bigger than 1000
// Numbers bigger than 1000 should be ignored.
//
//        Example:
//
//        Add("1001, 2") // 2
//
// 7. Separators can be of any length if
//  surrounded by square brackets.
//        Example:
//        Add("//[***]\n1***2***3") // 6

// 8. Allow multiple single-character separators like this: “//[delim1][delim2]\n”
// Example:
// Add("//[*][%]\n1*2%3") // 6
// 9. Handle multiple separators with any character length.
//
//        Example:
//        Add("//[foo][bar]\n1foo2bar3") // 6


import com.codurance.string_calculator.StringCalculator.MinusNumberNotAllowedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            "12,3; 15",
            "19,1,2; 22",
            "'19\n1'; 20",
            "'//;\n1;2'; 3",
            "'1001,2'; 2",
            "'//[***]\n1***2***3'; 6",
            "'//[*][%]\n1*2%3'; 6",
            "'//[***]\n1***2***3'; 6",
            "'//[foo][bar]\n1foo2bar3'; 6"
    }, delimiter = ';')
    void return_number_for_input(String input, int output) throws MinusNumberNotAllowedException {
        assertEquals(output, stringCalculator.add(input));
    }

    @Test
    void throws_exception_for_negative_numbers() {
        MinusNumberNotAllowedException exception = assertThrows(MinusNumberNotAllowedException.class, () ->
            stringCalculator.add("1,-2,-3"));

        assertEquals("negatives not allowed: -2 -3", exception.getMessage());
    }


    @ParameterizedTest
    @CsvSource(value = {
            "'//[***]\n1***2***3'; '1,2,3'",
            "'//[*][%]\n1*2%3'; '1,2,3'",
            "'//[!][$]\n1$2!3'; '1,2,3'",
            "'//[$$][££][%%]\n1$$2££3%%4'; '1,2,3,4'"
    }, delimiter = ';')
    void return_comma_separated_from_custom_separated(String input, String result) {
        assertEquals(result, stringCalculator.replaceCustomSeparator(input));
    }
}
