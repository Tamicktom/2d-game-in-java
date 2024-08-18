package src.main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

  // SCREEN SETTINGS
  final int originalTileSize = 16; // 16x16 tile
  final int scale = 3; // scale of the game

  final int tileSize = originalTileSize * scale; // 48x48 tile

  final int maxScreenColumns = 16; // 16 columns
  final int maxScreenRows = 12; // 12 rows

  final int screenWidth = tileSize * maxScreenColumns; // 768px
  final int screenHeight = tileSize * maxScreenRows; // 576px

  int FPS = 60; // frames per second

  KeyHandler keyH = new KeyHandler();
  Thread gameThread;

  // Set players default position
  int playerX = 100;
  int playerY = 100;
  int playerSpeed = 4;

  public GamePanel() {
    // set preferred size
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // Game Loop
  @Override
  public void run() {

    double drawInterval = 1_000_000_000 / FPS; // nanoseconds
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    while (gameThread != null) {

      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += currentTime - lastTime;
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
        drawCount++;
      }

      if (timer >= 1_000_000_000) {
        System.out.println("FPS: " + drawCount);
        timer = 0;
        drawCount = 0;
      }
    }
  }

  public void update() {
    if (keyH.upPressed) { // go up
      playerY -= playerSpeed;
    } else if (keyH.downPressed) { // go down
      playerY += playerSpeed;
    } else if (keyH.leftPressed) { // go left
      playerX -= playerSpeed;
    } else if (keyH.rightPressed) { // go right
      playerX += playerSpeed;
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.white); // white background
    g2d.fillRect(playerX, playerY, tileSize, tileSize); // draw a rectangle

    g2d.dispose(); // dispose the graphics
  }
}
