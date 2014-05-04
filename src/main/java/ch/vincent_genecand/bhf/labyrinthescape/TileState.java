package ch.vincent_genecand.bhf.labyrinthescape;

import java.awt.Color;

public enum TileState {

    WALL(Color.BLACK), EMPTY(Color.WHITE);

    private final Color color;

    private TileState(Color color) {
        this.color = color;
    }

    public final Color getColor() {
        return this.color;
    }

}
