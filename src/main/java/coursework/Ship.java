package coursework;

import java.awt.*;

public class Ship extends Block {
    private Image img;

    public Ship(int tileSize, int boardWidth, int boardHeight, Image img) {
        super(boardWidth / 2 - tileSize / 2, boardHeight - tileSize - 10, tileSize, tileSize);
        this.img = img;
    }

    public void moveLeft() {
        x = Math.max(x - width / 2, 0);
    }

    public void moveRight(int boardWidth) {
        x = Math.min(x + width / 2, boardWidth - width);
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }
}
