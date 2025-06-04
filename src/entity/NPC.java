package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class NPC extends Entity {
  private GamePanel gp;
  private String[] dialogueLines;

  public NPC(GamePanel gp, int worldX, int worldY, String[] dialogueLines) {
    this.gp = gp;
    this.worldX = worldX;
    this.worldY = worldY;
    this.dialogueLines = dialogueLines;
    direction = Direction.DOWN;

    solidArea = new Rectangle(8, 16, gp.TILE_SIZE - 16, gp.TILE_SIZE - 16);
    getNPCImage();
  }

  private void getNPCImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_up_1.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_down_1.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_left_1.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/walking/boy_right_1.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String[] getDialogueLines() {
    return dialogueLines;
  }

  public void draw(Graphics2D g2d) {
    BufferedImage image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
    int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

    g2d.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
  }
}
