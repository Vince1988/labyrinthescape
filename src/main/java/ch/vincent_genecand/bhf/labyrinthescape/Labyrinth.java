package ch.vincent_genecand.bhf.labyrinthescape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Labyrinth {

    public final static int TILE_SIZE = 10;

    private final int columns;
    private final int rows;
    private final Map<Point, Tile> tiles;

    public Labyrinth(int width, int height) {
        this.columns = width;
        this.rows = height;
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
        Tile t = this.getTileAt(1, 1);
        t.setState(TileState.EMPTY);
        this.nextTile(t);
        System.out.println("done");
    }

    private void nextTile(Tile tile) {
        for (Orientation o : Orientation.getAllShuffled()) {
            if (this.isTileAheadUsable(o, tile)) {
                this.getNeighbor(o, tile).setState(TileState.EMPTY);
                this.nextTile(this.getNeighbor(o, tile));
            }
        }
    }

    public boolean isTileAheadUsable(Orientation orientation, Tile tile) {
        Tile tileAhead = this.getNeighbor(orientation, tile);
        if (!this.checkTile(tileAhead)) {
            return false;
        } else {
            Tile front = this.getNeighbor(orientation, tile);
            if (!this.checkTile(front)) {
                return false;
            }

            for (Orientation o : Orientation.getAll()) {
                if (orientation != o && orientation.opposite() != o) {
                    Point p = Orientation.combination(orientation, o);
                    Point pos = tileAhead.getPosition();
                    pos.translate(p.x, p.y);
                    if (!this.checkTile(this.getNeighbor(o, tileAhead)) || !this.checkTile(this.getTileAt(pos))) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private boolean checkTile(Tile tile) {
        return !(tile == null || tile.getState() == TileState.EMPTY);
    }

    public Tile getNeighbor(Orientation orientation, Tile tile) {
        return this.getTileAt(orientation.translate(tile.getPosition()));
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
