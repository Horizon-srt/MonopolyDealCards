package utils;

public enum Color {
    BROWN,
    BLUE,
    GREEN,
    LIGHT_BLUE,
    ORANGE,
    PINK,
    RAILROAD,
    RED,
    UTILITY,
    YELLOW,
    ALL;

    public String toString(Color c) {
        return switch (c) {
            case BROWN -> "BROWN";
            case BLUE -> "BLUE";
            case GREEN -> "GREEN";
            case LIGHT_BLUE -> "LIGHT_BLUE";
            case ORANGE -> "ORANGE";
            case PINK -> "PINK";
            case RAILROAD -> "RAILROAD";
            case RED -> "RED";
            case UTILITY -> "UTILITY";
            case YELLOW -> "YELLOW";
            default -> "ALL";
        };
    }
}
