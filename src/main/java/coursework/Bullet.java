package coursework;

import java.awt.*;

public class Bullet extends Block {
    public static final int DEFAULT_WIDTH = 4;
    public static final int DEFAULT_HEIGHT = 10;
    private int velocityY;
    private boolean used;

    public Bullet(int x, int y, int width, int height, int velocityY) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.velocityY = velocityY;
        this.used = false;
    }

    public void move() {
        setY(getY() + velocityY);
    }

    public boolean isOutOfBounds(int height) {
        return getY() < 0 || getY() > height;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
