package coursework;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int tileSize = 32;
        int rows = 20;
        int columns = 40;
        int boardWidth = tileSize * columns;
        int boardHeight = tileSize * rows;

        JFrame frame = new JFrame("Space Invaders");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameBoard gameBoard = new GameBoard(tileSize, rows, columns);
        frame.add(gameBoard);
        frame.pack();
        gameBoard.requestFocus();
        frame.setVisible(true);
    }
}
