package thtroyer.day2;

import java.util.Arrays;
import java.util.Objects;

public enum BlockType {
    BLUE ("blue"),
    GREEN ("green"),
    RED ("red");
    private final String name;
    private BlockType(String name) {
        this.name = name;
    }

    public static BlockType from(String name) {
        return Arrays.stream(values())
                .map(v -> v.name.equals(name) ? v : null)
                .filter(Objects::nonNull)
                .findAny().orElseThrow(RuntimeException::new);
    }
}
