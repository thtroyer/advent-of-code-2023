package thtroyer.day1;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        System.out.print(line + " is ");

        var matches = Stream.concat(
                findAllNumberStrings(line).stream(),
                findAllNumerics(line).stream()
        ).sorted(Comparator.comparingInt(Match::index)).toList();

        return matches.getFirst().value() * 10
                + matches.getLast().value();
    }


    private String replaceFirst(String line) {
        var match = findFirstNumberString(line);
        return match
                .map(value -> line.replaceFirst(value.name(), String.valueOf(value.value)))
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

    private List<Match> findAllNumberStrings(String line) {
        return IntStream.range(0, line.length())
                .boxed()
                .map(i -> findAMatchAtIndex(line, i))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.comparingInt(Match::index))
                .toList();
    }

    private List<Match> findAllNumerics(String line) {
        return IntStream.range(0, line.length())
                .boxed()
                .filter(i -> Character.isDigit(line.charAt(i)))
                .map(i -> new Match(i, null, Integer.parseInt(line.substring(i, i+1))))
                .toList();
    }

    private Optional<Match> findAMatchAtIndex(String line, int index) {
        return numbers.entrySet().stream()
                .filter(n -> line.indexOf(n.getKey(), index) == index)
                .map(n -> new Match(index, n.getKey(), n.getValue()))
                .findAny();
    }

//    private Optional<Match> findLastNumberString(String line) {
//        var matches = numbers.entrySet().stream()
//                .map(m ->
//                        !line.contains(m.getKey()) ?
//                                Optional.empty() :
//                                Optional.of(new Match(
//                                        line.length() - reverse(line).indexOf(reverse(m.getKey())) - m.getKey().length(),
//                                        m.getKey(),
//                                        m.getValue()))
//                ).filter(Optional::isPresent)
//                .map(Optional::get)
//                .max((Match m) -> Comparator.comparingInt(m.index))
//        return matches;
//
////        return numbers.entrySet().stream()
////                .map(m -> new Match(
////                        line.length() - reverse(line).indexOf(reverse(m.getKey())) - m.getKey().length(),
////                        m.getKey(),
////                        m.getValue()))
////                .filter(m -> m.index() != -1)
////                .max(Comparator.comparingInt(Match::index));
//    }


    public static void main(String[] args) {
        System.out.println(new Day1().getCalibrationValue(args));
    }
}
