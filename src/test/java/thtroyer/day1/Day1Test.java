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
                Arguments.of("treb7uchet", 77)
        );
    }

    private static Stream<Arguments> getCalibrationValue_many_lines() {
        return Stream.of(
                Arguments.of("""
                        1abc2
                        pqr3stu8vwx
                        a1b2c3d4e5f
                        treb7uchet
                        """, 142)
        );
    }
}