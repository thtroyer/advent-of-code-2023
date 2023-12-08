package thtroyer.day2;

public record CubeCount (BlockType type, int count){
    public static CubeCount from(String type, int count) {
        return new CubeCount(BlockType.from(type), count);
    }
}
