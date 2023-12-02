package thtroyer.day1;

import java.util.*;

public class Day1 {
    record Match(int index, String name, int value) {}

    Map<String, Integer> numbers = Map.of(
           "one", 1,
           "two", 2,
           "three", 3,
           "four", 4,
           "five", 5,
           "six", 6,
           "seven", 7,
           "eight", 8,
           "nine", 9 );

    int getCalibrationValue(String ... lines) {
        return Arrays.stream(lines)
                .map(this::getCalibrationValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    protected int getCalibrationValue(String line) {
        line = replaceFirst(line);
        line = replaceLast(line);

        var numbers = line.chars()
                .mapToObj(i -> (char)i)
                .filter(Character::isDigit)
                .map(String::valueOf)
                .toList();

        return Integer.parseInt(numbers.getFirst() + numbers.getLast());
    }

    private String replaceFirst(String line) {
        var match = findFirstNumberString(line);
        return match
                .map(value -> line.replaceFirst(value.name(), String.valueOf(value.value)))
                .orElse(line);
    }

    private String replaceLast(String line) {
        var match = findLastNumberString(line);
        return match
                .map(value -> reverse(
                        reverse(line).replaceFirst(
                                reverse(value.name()),
                                String.valueOf(value.value))))
                .orElse(line);
    }

    private String reverse(String line) {
        return new StringBuilder(line).reverse().toString();
    }

    private Optional<Match> findFirstNumberString(String line) {
        return numbers.entrySet().stream()
                .map(m -> new Match(line.indexOf(m.getKey()), m.getKey(), m.getValue()))
                .filter(m -> m.index() != -1)
                .min(Comparator.comparingInt(Match::index));
    }

    private Optional<Match> findLastNumberString(String line) {
        return numbers.entrySet().stream()
                .map(m -> new Match(line.indexOf(m.getKey()), m.getKey(), m.getValue()))
                .filter(m -> m.index() != -1)
                .max(Comparator.comparingInt(Match::value));
    }


    public static void main(String[] args) {
        System.out.println(new Day1().getCalibrationValue(args));
    }
}
