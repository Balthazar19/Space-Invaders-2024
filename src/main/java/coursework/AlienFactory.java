package coursework;

import java.awt.Image;

public interface AlienFactory {
    Alien createAlien(int x, int y, int width, int height, Image img, GameController controller);
}