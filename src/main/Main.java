package src.main;

import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
    JFrame window = new JFrame();

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("My first Java game");

    // create the game panel
    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    window.pack();

    window.setLocationRelativeTo(null); // center the window
    window.setVisible(true);

    gamePanel.startGameThread();
  }
}