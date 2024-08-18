package src.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {
  GamePanel gp;
  KeyHandler keyH;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    x = 100;
    y = 100;
    speed = 4;
    direction = Direction.DOWN;
  }

  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_up_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_up_2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_down_1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_down_2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_left_1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_left_2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_right_1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/assets/boy_right_2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    if (keyH.upPressed) {
      direction = Direction.UP;
      y -= speed;
    } else if (keyH.downPressed) {
      direction = Direction.DOWN;
      y += speed;
    } else if (keyH.leftPressed) {
      direction = Direction.LEFT;
      x -= speed;
    } else if (keyH.rightPressed) {
      direction = Direction.RIGHT;
      x += speed;
    }
  }

  public void draw(Graphics2D g2d) {
    BufferedImage image = null;

    switch (direction) {
      case UP:
        image = up1;
        break;
      case DOWN:
        image = down1;
        break;
      case LEFT:
        image = left1;
        break;
      case RIGHT:
        image = right1;
        break;
      default:
        image = down1;
        break;
    }

    g2d.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
  }
}
