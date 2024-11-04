package coursework;

import java.awt.event.*;

public class KeyInputHandler implements KeyListener {
    private GameController controller;

    public KeyInputHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        Ship ship = controller.getShip();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> ship.moveLeft();
            case KeyEvent.VK_D -> ship.moveRight(controller.getBoardWidth());
            case KeyEvent.VK_SPACE -> {
                Bullet bullet = new Bullet(ship.getX() + ship.getWidth() / 2 - 2, ship.getY(), 4, 10, -10);
                controller.getBullets().add(bullet);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
