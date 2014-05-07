package ch.vincent_genecand.bfh.labyrinthescape.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.vincent_genecand.bfh.labyrinthescape.Labyrinth;
import ch.vincent_genecand.bfh.labyrinthescape.Orientation;
import ch.vincent_genecand.bfh.labyrinthescape.Tile;

public abstract class LabyrinthGenerator extends Thread {

    private final Labyrinth labyrinth;

    public LabyrinthGenerator(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    protected abstract void generate();

    protected final Tile getExitTile() {
        List<Tile> tiles = new ArrayList<>(this.labyrinth.getTiles());
        Collections.shuffle(tiles);

        for (Tile t : tiles) {
            for (Orientation o : Orientation.values()) {
                if (t.getNeighbor(this.labyrinth, o) == null) {
                    return t;
                }
            }
        }

        return null;
    }

    protected final static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public final void run() {
        this.generate();
    }

    public Labyrinth getLabyrinth() {
        return this.labyrinth;
    }

}
