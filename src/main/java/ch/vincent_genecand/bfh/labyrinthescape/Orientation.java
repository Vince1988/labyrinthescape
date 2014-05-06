package ch.vincent_genecand.bfh.labyrinthescape;

import java.awt.Point;
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

    public Point translate(Point p) {
        Point point = new Point(p);
        point.translate(this.dX, this.dY);

        return point;
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

    public static Point combination(Orientation orientationA, Orientation orientationB) {
        return new Point(orientationA.dX + orientationB.dX, orientationA.dY + orientationB.dY);
    }

    public static List<Orientation> getAll() {
        return Arrays.asList(Orientation.values());
    }

    public static List<Orientation> getAllShuffled() {
        List<Orientation> list = Orientation.getAll();
        Collections.shuffle(list);

        return list;
    }
}
