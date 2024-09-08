package org.GameObjects;

import com.diogonunes.jcdp.color.api.Ansi;
import org.game.Position;

public class Goal extends GameObject {

    public Goal(Position position) {
        super("Goal", position, true, 'O', Ansi.BColor.BLUE);
    }

}
