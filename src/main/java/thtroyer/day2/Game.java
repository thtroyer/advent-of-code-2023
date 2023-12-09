package thtroyer.day2;

import java.util.List;

public record Game (int id, List<List<CubeCount>> drawings) {
    public boolean isGamePossible(CubeCount maxC) {
        List<CubeCount> impossibleDrawings = drawings.stream()
                .flatMap(l -> l.stream()
                        .filter(cc -> cc.type().equals(maxC.type())))
                        .filter(cc -> cc.count() > maxC.count())
                .toList();
        
        return impossibleDrawings.isEmpty();
    }

    public int getPower() {
        return getPowerOf(BlockType.BLUE) * getPowerOf(BlockType.RED) * getPowerOf(BlockType.GREEN);
    }

    private int getPowerOf(BlockType type) {
        return drawings.stream()
                .flatMap(l -> l.stream()
                        .filter(cc -> cc.type().equals(type)).toList().stream())
                .map(CubeCount::count)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }
}
