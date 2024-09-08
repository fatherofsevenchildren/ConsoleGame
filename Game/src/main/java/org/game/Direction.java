package org.game;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public static Direction fromDeltas(int deltaX, int deltaY) {
        for (Direction direction : values()) {
            if (direction.getDeltaX() == deltaX && direction.getDeltaY() == deltaY) {
                return direction;
            }
        }
        throw new IllegalArgumentException("No direction with deltas: " + deltaX + ", " + deltaY);
    }
}

