package src.main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

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

  }
}
