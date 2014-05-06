package ch.vincent_genecand.bfh.labyrinthescape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Labyrinth {

    public final static int TILE_SIZE = 10;

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

    public void generate() {
        Tile t = this.getTileAt(0, this.rows / 2);
        t.setState(TileState.EMPTY);
        this.nextTile(t);
        System.out.println("done");
    }

    private void nextTile(Tile tile) {
        for (Orientation o : Orientation.getAllShuffled()) {
            if (this.isTileAheadUsable(o, tile)) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Tile t = tile.getNeighbor(this, o);
                t.setState(TileState.EMPTY);
                this.nextTile(t);
            }
        }
    }

    public boolean isTileAheadUsable(Orientation orientation, Tile tile) {
        Tile tileAhead = tile.getNeighbor(this, orientation);
        if (!this.checkTile(tileAhead)) {
            return false;
        } else {
            Tile front = tileAhead.getNeighbor(this, orientation);
            if (!this.checkTile(front)) {
                return false;
            }

            for (Orientation o : Orientation.values()) {
                if (orientation != o && orientation.opposite() != o) {
                    if (!this.checkTile(tileAhead.getNeighbor(this, o)) || !this.checkTile(tileAhead.getDiagonalNeighbor(this, orientation, o))) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private boolean checkTile(Tile tile) {
        return tile != null && tile.getState() == TileState.WALL;
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

}
