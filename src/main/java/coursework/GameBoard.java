package coursework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener {
    //public static int boardHeight;
    public int boardWidth;
    public static int boardHeight;
    private GameController gameController;
    private Timer gameLoop;

    public GameBoard(int tileSize, int rows, int columns) {
        this.boardWidth = tileSize * columns;
        this.boardHeight = tileSize * rows;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);
        setFocusable(true);

        gameController = new GameController(tileSize, rows, columns);
        addKeyListener(new KeyInputHandler(gameController));

        gameLoop = new Timer(1200 / 60, this);
        gameLoop.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.draw(g);
    }

    public void actionPerformed(ActionEvent e) {
        gameController.update();
        repaint();
        if (gameController.isGameOver()) {
            gameLoop.stop();
        }
    }
}
