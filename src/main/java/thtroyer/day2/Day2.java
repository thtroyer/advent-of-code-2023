package thtroyer.day2;

import java.util.Arrays;

public class Day2 {
    public int play(String[] games, int red, int blue, int green) {
        var redCount = new CubeCount(BlockType.RED, red);
        var blueCount = new CubeCount(BlockType.BLUE, blue);
        var greenCount = new CubeCount(BlockType.GREEN, green);

        Arrays.stream(games)
                .map(this::gameFromLine)

        return 1;
    }

    private Game gameFromLine(String line) {
        int index = Integer.parseInt(line.split(":")[0].split(" ")[1]);
        String[] gameStrings = (line.split(":")[1]).split(";");
        Arrays.stream(gameStrings)
                .flatMap(s -> {
                    String[] drawings = s.split(",");
                    Arrays.stream(drawings)
                            .map(String::trim)
                })
    }

}
