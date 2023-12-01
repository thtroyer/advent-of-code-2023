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
        line = convertEmbededNumbers(line);
        var numbers = line.chars()
                .mapToObj(i -> (char)i)
                .filter(Character::isDigit)
                .map(String::valueOf)
                .toList();

        return Integer.parseInt(numbers.getFirst() + numbers.getLast());
    }

    protected String convertEmbededNumbers(String line) {
        line = line.replace("one", "1");
        line = line.replace("two", "2");
        line = line.replace("three", "3");
        line = line.replace("four", "4");
        line = line.replace("five", "5");
        line = line.replace("six", "6");
        line = line.replace("seven", "7");
        line = line.replace("eight", "8");
        line = line.replace("nine", "9");
        return line;
    }

    public static void main(String[] args) {
        System.out.println(new Day1().getCalibrationValue(args));
    }
}
