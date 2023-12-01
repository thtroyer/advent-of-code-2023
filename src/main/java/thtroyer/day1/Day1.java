package thtroyer.day1;

import java.util.Arrays;

public class Day1 {
    int getCalibrationValue(String ... lines) {
        return Arrays.stream(lines)
                .map(this::getCalibrationValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    protected int getCalibrationValue(String line) {
        var numbers = line.chars()
                .mapToObj(i -> (char)i)
                .filter(Character::isDigit)
                .map(String::valueOf)
                .toList();

        return Integer.parseInt(numbers.getFirst() + numbers.getLast());
    }

    public static void main(String[] args) {
        System.out.println(new Day1().getCalibrationValue(args));
    }
}
