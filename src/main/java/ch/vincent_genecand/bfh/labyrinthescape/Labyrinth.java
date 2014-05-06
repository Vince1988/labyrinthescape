package ch.vincent_genecand.bfh.labyrinthescape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Labyrinth {

    public final static int TILE_SIZE = 3;

    private final int columns;
    private final int rows;
    private final Map<Point, Tile> tiles;

    public Labyrinth(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.tiles = new HashMap<>();

        this.initTiles();
    }

    private void initTiles() {
        for (int x = 0; x < this.columns; x++) {
            for (int y = 0; y < this.rows; y++) {
                Tile t = new Tile(x, y);
                this.tiles.put(t.getPosition(), t);
            }
        }
    }

    public void reset() {
        for (Tile tile : this.tiles.values()) {
            tile.setState(TileState.WALL);
        }
    }

    public Tile getTileAt(Point position) {
        return this.tiles.get(position);
    }

    public Tile getTileAt(int x, int y) {
        return this.getTileAt(new Point(x, y));
    }

    public void draw(Graphics2D g2) {
        for (Tile tile : this.tiles.values()) {
            tile.draw(g2, TILE_SIZE);
        }
    }

    public final int getColumns() {
        return this.columns;
    }

    public final int getRows() {
        return this.rows;
    }

    public final Collection<Tile> getTiles() {
        return Collections.unmodifiableCollection(this.tiles.values());
    }
}
