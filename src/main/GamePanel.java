package src.main;

import javax.swing.JPanel;

import src.entity.Player;
import src.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

  // SCREEN SETTINGS
  final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
  final int SCALE = 3; // scale of the game

  public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile

  public final int MAX_SCREEN_COLUMNS = 16; // 16 columns
  public final int MAX_SCREEN_ROWS = 12; // 12 rows

  final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // 768px
  final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // 576px

  final int FPS = 60; // frames per second

  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  Player player = new Player(this, keyH);
  TileManager tileManager = new TileManager(this);

  public GamePanel() {
    // set preferred size
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
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
    player.update();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    tileManager.draw(g2d);
    player.draw(g2d);

    g2d.dispose(); // dispose the graphics
  }
}
