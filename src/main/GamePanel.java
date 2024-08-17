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

  Thread gameThread;

  public GamePanel() {
    // set preferred size
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // Game Loop
  @Override
  public void run() {

    while (gameThread != null) {
      // 1 UPDATE: update information such as player position, enemy position, etc.
      // 2 DRAW: draw the game
      update();
      repaint();
    }
  }

  public void update() {
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.white); // white background
    g2d.fillRect(100, 100, tileSize, tileSize); // draw a rectangle

    g2d.dispose(); // dispose the graphics
  }
}
