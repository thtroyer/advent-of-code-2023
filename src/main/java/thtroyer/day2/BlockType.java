package thtroyer.day2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum BlockType {
    BLUE ("blue"),
    GREEN ("green"),
    RED ("red");
    private final String name;
    private BlockType(String name) {
        this.name = name;
    }

    public static BlockType from(String name) {

        BlockType b = Arrays.stream(values())
                .map(v -> v.name.equals(name) ? v : null)
                .filter(Objects::nonNull)
                .findAny().orElseThrow(RuntimeException::new);
        return b;
    }
}
