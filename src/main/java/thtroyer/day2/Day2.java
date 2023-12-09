package thtroyer.day2;

import java.util.Arrays;
import java.util.List;

public class Day2 {
    public int play(String[] gameStrings, int red, int blue, int green) {
        var maxRolls = List.of(
                new CubeCount(BlockType.RED, red),
                new CubeCount(BlockType.BLUE, blue),
                new CubeCount(BlockType.GREEN, green));

        var games = Arrays.stream(gameStrings)
                .map(this::gameFromLine)
                .toList();

        var possibleGames = games.stream()
                .filter(g -> maxRolls.stream()
                        .map(g::isGamePossible)
                        .reduce(true, (acc, res) -> acc && res))
                .toList();

        return possibleGames.stream()
                .map(Game::id)
                .reduce(0, Integer::sum);
    }

    private Game gameFromLine(String line) {
        int id = Integer.parseInt(line.split(":")[0].split(" ")[1]);
        String[] drawingStrings = (line.split(":")[1]).split(";");
        List<List<CubeCount>> drawings = Arrays.stream(drawingStrings)
                .map(s -> {
                    String[] drawingString = s.split(",");
                    return Arrays.stream(drawingString)
                            .map(String::trim)
                            .map(d -> CubeCount.from(d.split(" ")[1], Integer.parseInt(d.split(" ")[0])))
                            .toList();
                }).toList();

        return new Game(id, drawings);
    }

    public int power(String[] gameStrings) {
        return Arrays.stream(gameStrings)
                .map(this::gameFromLine)
                .map(Game::getPower)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
