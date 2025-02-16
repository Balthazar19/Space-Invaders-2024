package coursework;

import java.awt.*;

public class Alien extends Block {
    private boolean alive;
    private GameController controller;

    public Alien(int x, int y, int width, int height, Image img, GameController controller) {
        super(x, y, width, height);
        this.alive = true;
        this.img = img;
        this.controller = controller;
    }

    private Image img;

    public void move(int dx) {
        setX(getX() + dx);
    }

    public void moveDown(int dy) {
        setY(getY() + dy);
    }

    public void draw(Graphics g) {
        g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean canShoot(Ship ship) {
        return getX() + getWidth() / 2 >= ship.getX() && getX() + getWidth() / 2 <= ship.getX() + ship.getWidth() && alive;
    }

    public Bullet shoot() {
        return controller.createBullet(getX() + getWidth() / 2 - 2, getY() + getHeight(), 5);
    }
}
