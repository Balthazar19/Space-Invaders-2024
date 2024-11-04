package coursework;

import java.awt.*;

public class Alien extends Block {
    private boolean alive;

    public Alien(int x, int y, int width, int height, Image img) {
        super(x, y, width, height);
        this.alive = true;
        this.img = img;
    }

    private Image img;

    public void move(int dx) {
        x += dx;
    }

    public void moveDown(int dy) {
        y += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean canShoot(Ship ship) {
        return x + width / 2 >= ship.getX() && x + width / 2 <= ship.getX() + ship.getWidth() && alive;
    }

    public Bullet shoot() {
        return new Bullet(x + width / 2 - 2, y + height, 4, 10, 5);
    }
}
