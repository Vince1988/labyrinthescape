package ch.vincent_genecand.bfh.labyrinthescape;

import ch.vincent_genecand.bfh.labyrinthescape.gui.LabyrinthPanel;
import ch.vincent_genecand.bfh.labyrinthescape.gui.MainWindow;

public class Main {

    public static void main(String[] args) {
        Labyrinth l = new Labyrinth(200, 200);
        LabyrinthPanel p = new LabyrinthPanel(l);
        MainWindow w = new MainWindow(p);
        new Thread(w).start();

        // LabyrinthGenerator.iterativeGeneration(l);
        LabyrinthGenerator.recursiveGeneration(l);

    }
}
