package org.GameObjects;

import com.diogonunes.jcdp.color.api.Ansi;
import org.game.Position;

public abstract class GameObject {

    private final String name;
    private final Position position;
    /**
     * Если true, тогда через данный объект можно пройти насквозь
     */
    private final boolean isTransparent;
    private final Character character;
    private final Ansi.FColor fColor;
    private final Ansi.BColor bColor;

    public GameObject(String name, Position position, boolean isTransparent, Character character, Ansi.BColor bColor) {
        this.name = name;
        this.position = position;
        this.isTransparent = isTransparent;
        this.character = character;
        this.fColor = Ansi.FColor.BLACK;
        this.bColor = bColor;
    }

    public String getName() {
        return name;
    }

    public Character getChar() {
        return character;
    }

    public Position getPosition() {
        return position;
    }

    public Ansi.FColor getfColor() {
        return fColor;
    }

    public Ansi.BColor getbColor() {
        return bColor;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

}
