package thtroyer.day1;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day1Test {

    @ParameterizedTest
    @MethodSource
    void getCalibrationValue_single_line(String input, int expected) {
        Day1 day1 = new Day1();

        assertThat(
                day1.getCalibrationValue(input)
        ).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void getCalibrationValue_many_lines(String input, int expected) {
        Day1 day1 = new Day1();

        assertThat(
                day1.getCalibrationValue(input.split("\n"))
        ).isEqualTo(expected);
    }

    private static Stream<Arguments> getCalibrationValue_single_line() {
        return Stream.of(
                Arguments.of("1abc2", 12),
                Arguments.of("pqr3stu8vwx", 38),
                Arguments.of("a1b2c3d4e5f", 15),
                Arguments.of("treb7uchet", 77),
                // part 2
                Arguments.of("two1nine", 29),
                Arguments.of("eightwothree", 83),
                Arguments.of("abcone2threexyz", 13),
                Arguments.of("xtwone3four", 24),
                Arguments.of("4nineeightseven2", 42),
                Arguments.of("zoneight234", 14),
                Arguments.of("7pqrstsixteen", 76)
        );
    }

    private static Stream<Arguments> getCalibrationValue_many_lines() {
        return Stream.of(
                Arguments.of("""
                        1abc2
                        pqr3stu8vwx
                        a1b2c3d4e5f
                        treb7uchet""", 142),
                Arguments.of("""
                        two1nine
                        eightwothree
                        abcone2threexyz
                        xtwone3four
                        4nineeightseven2
                        zoneight234
                        7pqrstsixteen""", 281)
        );
    }
}