package org.game;

import java.util.Objects;

public class Position {
    private int posX;
    private int posY;

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Position(Position pos) {
        this.posX = pos.posX;
        this.posY = pos.posY;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return this.getPosX() == position.getPosX() && this.getPosY() == position.getPosY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void changePosition(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

    public void changePosition(Direction direction) {
        this.posX += direction.getDeltaX();
        this.posY += direction.getDeltaY();
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setPosition(Position position) {
        this.posX = position.getPosX();
        this.posY = position.getPosY();
    }



}
