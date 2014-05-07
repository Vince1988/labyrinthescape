package ch.vincent_genecand.bfh.labyrinthescape.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ch.vincent_genecand.bfh.labyrinthescape.Labyrinth;
import ch.vincent_genecand.bfh.labyrinthescape.Orientation;
import ch.vincent_genecand.bfh.labyrinthescape.Tile;
import ch.vincent_genecand.bfh.labyrinthescape.TileStack;
import ch.vincent_genecand.bfh.labyrinthescape.TileState;

public class LabyrinthGenerator {

    private LabyrinthGenerator() {
        // private constructor
    }

    public final static void iterativeGeneration(Labyrinth labyrinth) {
        labyrinth.reset();

        TileStack stack = new TileStack();
        Tile startTile = LabyrinthGenerator.getExitTile(labyrinth);
        stack.push(startTile);

        while (!stack.isEmpty()) {
            Tile tile = stack.lastElement();
            Iterator<Orientation> orientations = Orientation.getAllShuffled().iterator();

            LabyrinthGenerator.sleep(1);

            Tile nextTile = null;
            while (nextTile == null && orientations.hasNext()) {
                Orientation o = orientations.next();
                nextTile = tile.getNeighbor(labyrinth, o);
                if (nextTile != null) {
                    for (Tile neighbor : nextTile.getAllNeighborsOriented(labyrinth, o)) {
                        if (neighbor == null || neighbor.getState() != TileState.WALL) {
                            nextTile = null;
                        }
                    }
                }
            }

            if (nextTile == null || nextTile.getState() != TileState.WALL) {
                stack.pop();
            } else {
                if (!orientations.hasNext()) {
                    stack.pop();
                }
                stack.push(nextTile);
            }

        }
    }

    public final static void recursiveGeneration(Labyrinth labyrinth) {
        Tile firstTile = LabyrinthGenerator.getExitTile(labyrinth);
        labyrinth.reset();
        LabyrinthGenerator.recursion(firstTile, labyrinth);
    }

    private final static void recursion(Tile t, Labyrinth labyrinth) {
        t.setState(TileState.ACTIVE);

        LabyrinthGenerator.sleep(1);

        for (Orientation orientation : Orientation.getAllShuffled()) {
            Tile nextTile = t.getNeighbor(labyrinth, orientation);
            if (nextTile != null) {
                List<Tile> neighbors = nextTile.getAllNeighborsOriented(labyrinth, orientation);

                boolean useable = true;
                for (Tile neighbor : neighbors) {
                    if (neighbor == null || neighbor.getState() != TileState.WALL) {
                        useable = false;
                    }
                }

                if (useable) {
                    LabyrinthGenerator.recursion(nextTile, labyrinth);
                }
            }
        }
        t.setState(TileState.EMPTY);
    }

    private final static Tile getExitTile(Labyrinth labyrinth) {
        List<Tile> tiles = new ArrayList<>(labyrinth.getTiles());
        Collections.shuffle(tiles);

        for (Tile t : tiles) {
            for (Orientation o : Orientation.values()) {
                if (t.getNeighbor(labyrinth, o) == null) {
                    return t;
                }
            }
        }

        return null;
    }

    private final static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
