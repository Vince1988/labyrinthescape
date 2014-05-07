package ch.vincent_genecand.bfh.labyrinthescape.generators;

import java.util.List;

import ch.vincent_genecand.bfh.labyrinthescape.Labyrinth;
import ch.vincent_genecand.bfh.labyrinthescape.Orientation;
import ch.vincent_genecand.bfh.labyrinthescape.Tile;
import ch.vincent_genecand.bfh.labyrinthescape.TileState;

public class RecursiveLabyrinthGenerator extends LabyrinthGenerator {

    public RecursiveLabyrinthGenerator(Labyrinth labyrinth) {
        super(labyrinth);
    }

    private final void recursiveGeneration() {
        Tile firstTile = this.getExitTile();
        this.getLabyrinth().reset();
        this.recursion(firstTile);
    }

    private final void recursion(Tile t) {
        Labyrinth labyrinth = this.getLabyrinth();

        LabyrinthGenerator.pause(1);
        t.setState(TileState.ACTIVE);

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
                    this.recursion(nextTile);
                }
            }
        }
        LabyrinthGenerator.pause(1);
        t.setState(TileState.EMPTY);
    }

    @Override
    protected void generate() {
        this.recursiveGeneration();
    }

}
