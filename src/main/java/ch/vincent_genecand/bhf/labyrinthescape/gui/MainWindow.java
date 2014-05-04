package ch.vincent_genecand.bhf.labyrinthescape.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private static final String TITLE = "Labyrinth";

    public MainWindow(JPanel mainPanel) {
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setUndecorated(true);

        this.setContentPane(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            this.repaint();
        }

    }

}
