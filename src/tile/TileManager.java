package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class TileManager {

  GamePanel gp;
  Tile[] tile;
  int mapTileNum[][];

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tile = new Tile[10];
    mapTileNum = new int[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];

    getTileImage();
    loadMap("/assets/maps/001.txt");
  }

  public void getTileImage() {
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/000.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/001.png"));

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/002.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadMap(String filePath) {
    try {
      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

      int column = 0;
      int row = 0;

      while (column < gp.MAX_SCREEN_COLUMNS && row < gp.MAX_SCREEN_ROWS) {
        String line = br.readLine();

        while (column < gp.MAX_SCREEN_COLUMNS) {
          String numbers[] = line.split("-");

          int num = Integer.parseInt(numbers[column]);

          mapTileNum[column][row] = num;
          column++;
        }

        if (column == gp.MAX_SCREEN_COLUMNS) {
          column = 0;
          row++;
        }
      }

      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2d) {

    int column = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    while (column < gp.MAX_SCREEN_COLUMNS && row < gp.MAX_SCREEN_ROWS) {

      int tileNum = mapTileNum[column][row];

      g2d.drawImage(tile[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);

      column++;
      x += gp.TILE_SIZE;

      if (column == gp.MAX_SCREEN_COLUMNS) {
        column = 0;
        row++;
        x = 0;
        y += gp.TILE_SIZE;
      }
    }
  }
}
