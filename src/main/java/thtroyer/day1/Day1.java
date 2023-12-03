package thtroyer.day1;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Day1 {
    record Match(int index, String name, int value) {
    }

    Map<String, Integer> numbers = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    /**
     * Takes in all lines, returns summed answer
     */
    int getCalibrationValue(String... lines) {
        return Arrays.stream(lines)
                .map(this::getCalibrationValue)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    /**
     * Takes a single line and returns the value for that line,
     * which is the first and last number (numerical or text).
     */
    protected String getCalibrationValue(String line) {
        var matches = Stream.concat(
                        findAllNumberStrings(line).stream(),
                        findAllNumerics(line).stream()
                ).sorted(Comparator.comparingInt(Match::index))
                .toList();

        return "" + matches.getFirst().value() + matches.getLast().value();
    }

    /**
     * Find all the strings of written numbers (e.g. "one")
     *
     * @return List of Matches
     */
    private List<Match> findAllNumberStrings(String line) {
        return IntStream.range(0, line.length())
                .boxed()
                .map(i -> findAMatchAtIndex(line, i))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.comparingInt(Match::index))
                .toList();
    }


    private Optional<Match> findAMatchAtIndex(String line, int index) {
        return numbers.entrySet().stream()
                .filter(n -> line.indexOf(n.getKey(), index) == index)
                .map(n -> new Match(index, n.getKey(), n.getValue()))
                .findAny();
    }

    /**
     * Find all the strings of digits (e.g. "1")
     *
     * @return List of Matches
     */
    private List<Match> findAllNumerics(String line) {
        return IntStream.range(0, line.length())
                .boxed()
                .filter(i -> Character.isDigit(line.charAt(i)))
                .map(i -> new Match(i, null, Integer.parseInt(line.substring(i, i + 1))))
                .toList();
    }

    public static void main(String[] args) {
        System.out.println(new Day1().getCalibrationValue(args));
    }
}
