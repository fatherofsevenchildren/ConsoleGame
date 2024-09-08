package org.GameObjects;

import com.diogonunes.jcdp.color.api.Ansi;
import org.game.Position;

public class Obstacle extends GameObject {
    public Obstacle(Position position) {
        super("Obstacle", position, false, '#', Ansi.BColor.MAGENTA);
    }
}
