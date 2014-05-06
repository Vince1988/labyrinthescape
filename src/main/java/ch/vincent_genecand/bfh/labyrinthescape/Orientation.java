package ch.vincent_genecand.bfh.labyrinthescape;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Orientation {
    N(0, -1), E(1, 0), S(0, 1), W(-1, 0);

    private final int dX;
    private final int dY;

    private Orientation(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public int getdX() {
        return this.dX;
    }

    public int getdY() {
        return this.dY;
    }

    public Orientation opposite() {
        switch (this) {
        case N:
            return S;
        case E:
            return W;
        case S:
            return N;
        case W:
            return E;
        default:
            return this;
        }
    }

    public static List<Orientation> getAllShuffled() {
        List<Orientation> list = Arrays.asList(Orientation.values());
        Collections.shuffle(list);

        return list;
    }
}
