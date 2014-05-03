package ch.vincent_genecand.bhf.labyrinthescape;

import ch.vincent_genecand.bhf.labyrinthescape.gui.LabyrinthPanel;
import ch.vincent_genecand.bhf.labyrinthescape.gui.MainWindow;

public class Main {

    public static void main(String[] args) {
        Labyrinth l = new Labyrinth(100, 100);
        LabyrinthPanel p = new LabyrinthPanel(l);
        MainWindow w = new MainWindow(p);

    }

}
