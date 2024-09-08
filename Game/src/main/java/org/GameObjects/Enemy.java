package org.GameObjects;

import algoritms.ChaseLogic;
import com.diogonunes.jcdp.color.api.Ansi;
import org.game.Direction;
import org.game.GameMap;
import org.game.Moveable;
import org.game.Position;

public class Enemy extends GameObject implements Moveable {

    public Enemy(Position position) {
        super("Enemy", position, false, 'X', Ansi.BColor.RED);
    }

    @Override
    public void move(Direction direction, GameMap gameMap) {
        Hero hero = (Hero) gameMap.getObjectByName("Hero");
        int[] intPosition = ChaseLogic.getMove(this.getPosition().getPosX(), this.getPosition().getPosY(), hero.getPosition().getPosX(), hero.getPosition().getPosY());
        Direction direction1 = Direction.fromDeltas(intPosition[1], intPosition[0]);
        Position newPosition = new Position(this.getPosition());
        newPosition.changePosition(direction1);

        if (newPosition.getPosX() < 0 || newPosition.getPosX() >= gameMap.getMapSize() ||
                newPosition.getPosY() < 0 || newPosition.getPosY() >= gameMap.getMapSize()) {
            return;
        }

        GameObject gameObject = gameMap.getObjectByPosition(newPosition);
        if (gameObject != null && !(gameObject instanceof Obstacle) && !(gameObject instanceof Enemy)) {
            this.getPosition().setPosition(newPosition);
        }


        if (this.getPosition().equals(hero.getPosition())) {
            hero.kill();
        }
    }

}
