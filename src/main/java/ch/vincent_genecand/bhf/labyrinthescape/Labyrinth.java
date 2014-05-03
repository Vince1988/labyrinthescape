package ch.vincent_genecand.bhf.labyrinthescape;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

    private final static int SIZE = 10;

    private final int width;
    private final int height;
    private final List<Tile> tiles;

    public Labyrinth(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new ArrayList<>();

        this.initTiles();
    }

    private void initTiles() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.tiles.add(new Tile(x, y));
            }
        }
    }

    public void draw(Graphics2D g2) {

        for (Tile tile : this.tiles) {
            tile.draw(g2, SIZE);
        }

    }

    public static int getSize() {
        return SIZE;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
