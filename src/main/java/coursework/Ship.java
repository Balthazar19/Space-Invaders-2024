package coursework;

import java.awt.*;

public class Ship extends Block {
    private Image img;

    public Ship(int tileSize, int boardWidth, int boardHeight, Image img) {
        super(boardWidth / 2 - tileSize / 2, boardHeight - tileSize - 10, tileSize, tileSize);
        this.img = img;
    }

    public void moveLeft() {
        setX(Math.max(getX() - getWidth() / 2, 0));
    }

    public void moveRight(int boardWidth) {
        setX(Math.min(getX() + getWidth() / 2, boardWidth - getWidth()));
    }

    public void draw(Graphics g) {
        g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
    }
}
