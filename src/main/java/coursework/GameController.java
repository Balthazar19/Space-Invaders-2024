package coursework;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class GameController {
    private int tileSize, boardWidth, boardHeight;
    private Ship ship;
    private ArrayList<Alien> aliens;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> alienBullets;
    private int alienColumns = 10, alienRows = 5;
    private int score = 0;
    private boolean gameOver = false;
    private int alienVelocityX = 2;
    private Random random = new Random();
    private int highScore = 0;

    private Image shipImage;
    private Image alienImage;

    private AlienFactory alienFactory;

    public GameController(int tileSize, int rows, int columns) {
        this.tileSize = tileSize;
        this.boardWidth = tileSize * columns;
        this.boardHeight = tileSize * rows;

        shipImage = new ImageIcon("C:\\Users\\ejbingelis\\Desktop\\Space Invaders 2024\\src\\main\\resources\\coursework\\ship.png").getImage();
        alienImage = new ImageIcon("C:\\Users\\ejbingelis\\Desktop\\Space Invaders 2024\\src\\main\\resources\\coursework\\alien.png").getImage();

        ship = new Ship(tileSize, boardWidth, boardHeight, shipImage);
        aliens = new ArrayList<>();
        bullets = new ArrayList<>();
        alienBullets = new ArrayList<>();

        alienFactory = new StandardAlienFactory();
        createAliens();
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void update() {
        if (!gameOver) {
            moveAliens();
            moveBullets();
            moveAlienBullets();
            checkCollisions();
            checkGameStatus();
        }
    }

    public Bullet createBullet(int x, int y, int velocityY) {
        return new Bullet(x, y, Bullet.DEFAULT_WIDTH, Bullet.DEFAULT_HEIGHT, velocityY);
    }

    private void moveAlienBullets() {
        alienBullets.removeIf(bullet -> bullet.isOutOfBounds(boardHeight) || bullet.isUsed());
        for (Bullet bullet : alienBullets) {
            bullet.move();
        }
    }

    private void createAliens() {
        for (int c = 0; c < alienColumns; c++) {
            for (int r = 0; r < alienRows; r++) {
                Alien alien = alienFactory.createAlien(tileSize + c * tileSize * 2, tileSize + r * tileSize, tileSize * 2, tileSize, alienImage, this);
                aliens.add(alien);
            }
        }
    }

    private void moveAliens() {
        for (Alien alien : aliens) {
            if (alien.isAlive()) {
                alien.move(alienVelocityX);
                if (alien.getX() + alien.getWidth() >= boardWidth || alien.getX() <= 0) {
                    alienVelocityX *= -1;
                    for (Alien a : aliens) {
                        a.moveDown(tileSize);
                    }
                    break;
                }
                if (alien.getY() >= ship.getY()) {
                    gameOver = true;
                }
            }
        }

        for (Alien alien : aliens) {
            if (alien.canShoot(ship) && random.nextInt(100) < 2) {
                alienBullets.add(alien.shoot());
            }
        }
    }

    private void moveBullets() {
        bullets.removeIf(bullet -> bullet.isOutOfBounds(boardHeight) || bullet.isUsed());
        for (Bullet bullet : bullets) {
            bullet.move();
        }
    }

    private void checkCollisions() {
        for (Bullet bullet : bullets) {
            if (!bullet.isUsed()) {
                for (Alien alien : aliens) {
                    if (alien.isAlive() && detectCollision(bullet, alien)) {
                        bullet.setUsed(true);
                        alien.setAlive(false);
                        score += 100;
                        break;
                    }
                }
            }
        }

        for (Bullet bullet : alienBullets) {
            if (!bullet.isUsed() && detectCollision(bullet, ship)) {
                bullet.setUsed(true);
                gameOver = true;
            }
        }
    }

    private boolean detectCollision(Bullet bullet, Block block) {
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        Rectangle blockRect = new Rectangle(block.getX(), block.getY(), block.getWidth(), block.getHeight());
        return bulletRect.intersects(blockRect);
    }

    private void checkGameStatus() {
        if (aliens.stream().noneMatch(Alien::isAlive)) {
            alienColumns++;
            alienRows++;
            aliens.clear();
            bullets.clear();
            createAliens();
        }

        if (score > highScore) {
            highScore = score;
        }
    }

    public void draw(Graphics g) {
        if (gameOver) {
            g.setColor(Color.black);
            g.fillRect(0, 0, boardWidth, boardHeight);

            drawGameOverScores(g);
        } else {
            ship.draw(g);
            for (Alien alien : aliens) {
                if (alien.isAlive()) alien.draw(g);
            }
            for (Bullet bullet : bullets) {
                if (!bullet.isUsed()) bullet.draw(g);
            }
            for (Bullet bullet : alienBullets) {
                if (!bullet.isUsed()) bullet.draw(g);
            }
            drawScore(g);
        }
    }

    private void drawGameOverScores(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        String gameOverText = "Game Over";
        String currentScoreText = "Score: " + score;
        String highScoreText = "High Score: " + highScore;

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int gameOverWidth = metrics.stringWidth(gameOverText);
        int currentScoreWidth = metrics.stringWidth(currentScoreText);
        int highScoreWidth = metrics.stringWidth(highScoreText);

        int xGameOver = (boardWidth - gameOverWidth) / 2;
        int xCurrentScore = (boardWidth - currentScoreWidth) / 2;
        int xHighScore = (boardWidth - highScoreWidth) / 2;

        g.drawString(gameOverText, xGameOver, boardHeight / 2 - 40);
        g.drawString(currentScoreText, xCurrentScore, boardHeight / 2);
        g.drawString(highScoreText, xHighScore, boardHeight / 2 + 40);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        String scoreText = "" + score;

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int stringWidth = metrics.stringWidth(scoreText);

        int x = (boardWidth - stringWidth) / 2;

        g.drawString(scoreText, x, 35);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Ship getShip() {
        return ship;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}