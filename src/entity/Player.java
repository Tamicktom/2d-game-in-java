package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {
  GamePanel gp;
  KeyHandler keyH;

  public final int SCREEN_X, SCREEN_Y;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    SCREEN_X = (gp.SCREEN_WIDTH / 2) - (gp.TILE_SIZE / 2);
    SCREEN_Y = (gp.SCREEN_HEIGHT / 2) - (gp.TILE_SIZE / 2);

    solidArea = new Rectangle(8, 16, gp.TILE_SIZE - 16, gp.TILE_SIZE - 16);

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = 48;
    worldY = 48;
    speed = 4;
    direction = Direction.DOWN;
  }

  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_up_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_up_2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_down_1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_down_2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_left_1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_left_2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_right_1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_right_2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    if (keyH.isWalking) {
      if (keyH.upPressed) {
        direction = Direction.UP;
      } else if (keyH.downPressed) {
        direction = Direction.DOWN;
      } else if (keyH.leftPressed) {
        direction = Direction.LEFT;
      } else if (keyH.rightPressed) {
        direction = Direction.RIGHT;
      }

      // Check tile collision
      collisionOn = false;
      gp.collisionChecker.checkTile(this);

      // If collision, stop moving
      if (collisionOn == false) {
        switch (direction) {
          case UP:
            worldY -= speed;
            break;
          case DOWN:
            worldY += speed;
            break;
          case LEFT:
            worldX -= speed;
            break;
          case RIGHT:
            worldX += speed;
            break;
        }
      }

      spriteCounter++;

      if (spriteCounter >= 10) {
        if (spriteNumber == 1) {
          spriteNumber = 2;
        } else if (spriteNumber == 2) {
          spriteNumber = 1;
        }
        spriteCounter = 0;
      }
    }
  }

  public void draw(Graphics2D g2d) {
    BufferedImage image = null;

    switch (direction) {
      case UP:
        if (spriteNumber == 1) {
          image = up1;
        } else if (spriteNumber == 2) {
          image = up2;
        }
        break;
      case DOWN:
        if (spriteNumber == 1) {
          image = down1;
        } else if (spriteNumber == 2) {
          image = down2;
        }
        break;
      case LEFT:
        if (spriteNumber == 1) {
          image = left1;
        } else if (spriteNumber == 2) {
          image = left2;
        }
        break;
      case RIGHT:
        if (spriteNumber == 1) {
          image = right1;
        } else if (spriteNumber == 2) {
          image = right2;
        }
        break;
      default:
        image = down1;
        break;
    }

    g2d.drawImage(image, SCREEN_X, SCREEN_Y, gp.TILE_SIZE, gp.TILE_SIZE, null);
  }
}
