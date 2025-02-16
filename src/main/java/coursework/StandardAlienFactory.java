package coursework;

import java.awt.Image;

public class StandardAlienFactory implements AlienFactory {

    @Override
    public Alien createAlien(int x, int y, int width, int height, Image img, GameController controller) {
        return new Alien(x, y, width, height, img, controller);
    }
}