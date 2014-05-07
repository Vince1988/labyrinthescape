package ch.vincent_genecand.bfh.labyrinthescape.generators;

import java.util.Iterator;

import ch.vincent_genecand.bfh.labyrinthescape.Labyrinth;
import ch.vincent_genecand.bfh.labyrinthescape.Orientation;
import ch.vincent_genecand.bfh.labyrinthescape.Tile;
import ch.vincent_genecand.bfh.labyrinthescape.TileStack;
import ch.vincent_genecand.bfh.labyrinthescape.TileState;

public class IterativeLabyrinthGenerator extends LabyrinthGenerator {

    public IterativeLabyrinthGenerator(Labyrinth labyrinth) {
        super(labyrinth);
    }

    @Override
    protected void generate() {
        Labyrinth labyrinth = this.getLabyrinth();

        labyrinth.reset();

        TileStack stack = new TileStack();
        Tile startTile = this.getExitTile();

        stack.push(startTile);

        while (!stack.isEmpty()) {
            Tile tile = stack.lastElement();
            Iterator<Orientation> orientations = Orientation.getAllShuffled().iterator();

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

            LabyrinthGenerator.pause(1);
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

}
