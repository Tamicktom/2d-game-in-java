package src.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {
  GamePanel gp;
  KeyHandler keyH;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    setDefaultValues();
  }

  public void setDefaultValues() {
    x = 100;
    y = 100;
    speed = 4;
  }

  public void update() {
    if (keyH.upPressed) { // go up
      y -= speed;
    } else if (keyH.downPressed) { // go down
      y += speed;
    } else if (keyH.leftPressed) { // go left
      x -= speed;
    } else if (keyH.rightPressed) { // go right
      x += speed;
    }
  }

  public void draw(Graphics2D g2d) {

    g2d.setColor(Color.white); // white background
    g2d.fillRect(x, y, gp.TILE_SIZE, gp.TILE_SIZE); // draw a rectangle
  }
}
