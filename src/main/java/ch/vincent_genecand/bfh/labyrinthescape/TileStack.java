package ch.vincent_genecand.bfh.labyrinthescape;

import java.util.Stack;

public class TileStack extends Stack<Tile> {

    private static final long serialVersionUID = 1L;

    @Override
    public Tile push(Tile item) {
        item.setState(TileState.ACTIVE);
        return super.push(item);
    }

    @Override
    public synchronized Tile pop() {
        Tile item = super.pop();
        item.setState(TileState.EMPTY);
        return item;
    }

}
