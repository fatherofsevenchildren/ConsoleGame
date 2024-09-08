package org.GameObjects;

import com.diogonunes.jcdp.color.api.Ansi;
import org.game.Position;

public class EmptyObject extends GameObject {
    public EmptyObject(Position position) {
        super("EmptyObject", position, true, ' ', Ansi.BColor.YELLOW);
    }
}
