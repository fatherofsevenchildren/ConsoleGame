package org.GameObjects;

import com.diogonunes.jcdp.color.api.Ansi;
import org.game.Direction;
import org.game.GameMap;
import org.game.Moveable;
import org.game.Position;

public class Hero extends GameObject implements Moveable {

    private boolean isAlive;

    public Hero(Position position) {
        super("Hero", position, false, 'o', Ansi.BColor.GREEN);
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        this.isAlive = false;
    }

    @Override
    public void move(Direction direction, GameMap gameMap) {
        int currentPosX = getPosition().getPosX();
        int currentPosY = getPosition().getPosY();

        int newPosX = currentPosX + direction.getDeltaX();
        int newPosY = currentPosY + direction.getDeltaY();

        if (newPosX < 0 || newPosX >= gameMap.getMapSize() || newPosY < 0 || newPosY >= gameMap.getMapSize()) {
            return;
        }

        Position newPosition = new Position(newPosX, newPosY);

        boolean isTransparent = true;
        GameObject gameObject = gameMap.getObjectByPosition(newPosition);
        if (gameObject != null && !gameObject.isTransparent() && gameObject.getPosition().equals(newPosition)) {
            isTransparent = false;
        }

        if (isTransparent && isAlive) {
            getPosition().changePosition(newPosX - currentPosX, newPosY - currentPosY);
        }
    }
}
