package ch.vincent_genecand.bfh.labyrinthescape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Tile {

    private final int x;
    private final int y;
    private TileState state;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = TileState.WALL;
    }

    public void draw(Graphics2D g2, int size) {
        Rectangle r = new Rectangle(this.x * size, this.y * size, size, size);

        g2.setColor(this.state.getColor());
        g2.fill(r);
    }

    public Tile getNeighbor(Labyrinth labyrinth, Orientation orient) {
        return labyrinth.getTileAt(this.x + orient.getdX(), this.y + orient.getdY());
    }

    public Tile getDiagonalNeighbor(Labyrinth labyrinth, Orientation orientA, Orientation orientB) {
        if (orientA == orientB || orientA == orientB.opposite()) {
            return this;
        }

        int posX = this.x + orientA.getdX() + orientB.getdX();
        int posY = this.y + orientA.getdY() + orientB.getdY();

        return labyrinth.getTileAt(posX, posY);
    }

    public Point getPosition() {
        return new Point(this.x, this.y);
    }

    /* Getters & Setters */

    public TileState getState() {
        return this.state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Tile [state=" + this.state + ", x=" + this.x + ", y=" + this.y + "]";
    }
}
