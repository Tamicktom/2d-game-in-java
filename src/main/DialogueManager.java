package src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class DialogueManager {
  private GamePanel gp;
  private String[] lines = new String[0];
  private int currentLine = 0;
  private boolean active = false;

  public DialogueManager(GamePanel gp) {
    this.gp = gp;
  }

  public void startDialogue(String[] lines) {
    if (lines == null || lines.length == 0) return;
    this.lines = lines;
    this.currentLine = 0;
    this.active = true;
  }

  public void progress() {
    if (!active) return;
    currentLine++;
    if (currentLine >= lines.length) {
      active = false;
    }
  }

  public boolean isActive() {
    return active;
  }

  public void draw(Graphics2D g2) {
    if (!active) return;

    int margin = 16;
    int x = margin;
    int y = gp.SCREEN_HEIGHT - gp.TILE_SIZE * 2;
    int width = gp.SCREEN_WIDTH - margin * 2;
    int height = gp.TILE_SIZE * 2 - margin;

    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRoundRect(x, y, width, height, 10, 10);

    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20f));
    g2.drawString(lines[currentLine], x + 20, y + 30);
  }
}
