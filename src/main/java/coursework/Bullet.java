package coursework;

import java.awt.*;

public class Bullet extends Block {
    private int velocityY;
    private boolean used;

    public Bullet(int x, int y, int width, int height, int velocityY) {
        super(x, y, width, height);
        this.velocityY = velocityY;
        this.used = false;
    }

    public void move() {
        y += velocityY;
    }

    public boolean isOutOfBounds(int height) {
        return y < 0 || y > height;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
}
