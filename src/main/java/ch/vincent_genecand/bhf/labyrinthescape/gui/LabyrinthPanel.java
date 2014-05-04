package ch.vincent_genecand.bhf.labyrinthescape.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import ch.vincent_genecand.bhf.labyrinthescape.Labyrinth;

public class LabyrinthPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Labyrinth labyrinth;

    public LabyrinthPanel(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;

        this.setPreferredSize(new Dimension(this.labyrinth.getColumns() * Labyrinth.TILE_SIZE, this.labyrinth.getRows() * Labyrinth.TILE_SIZE));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        this.labyrinth.draw(g2);

    }

}
