package ch.vincent_genecand.bhf.labyrinthescape;

import java.awt.Graphics2D;

public class Tile {

    private TileState state;
    private final int x;
    private final int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2, int size) {
        g2.drawRect(this.x * Labyrinth.getSize(), this.y * Labyrinth.getSize(), Labyrinth.getSize(), Labyrinth.getSize());
    }

    public void toggleState() {
        switch (this.state) {
        case WALL:
            this.state = TileState.EMPTY;
            break;
        case EMPTY:
            this.state = TileState.WALL;
            break;
        default:
            break;
        }
    }
}
