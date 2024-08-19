package src.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class TileManager {

  final int QTD_TILES = 37;

  GamePanel gp;
  Tile[] tiles;
  int mapTileNum[][];

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tiles = new Tile[QTD_TILES];
    mapTileNum = new int[gp.MAX_WORLD_COLUMNS][gp.MAX_WORLD_ROWS];

    getTileImage();
    loadMap("/assets/maps/001.txt");
  }

  public void getTileImage() {
    try {
      for (int i = 0; i < QTD_TILES; i++) {
        // create tile number with a min of 2 "0" on the left
        String tileNum = String.format("%03d", i);
        String tilePath = "/assets/tiles/" + tileNum + ".png";

        System.out.println(tilePath);

        tiles[i] = new Tile();
        tiles[i].image = ImageIO.read(getClass().getResourceAsStream(tilePath));
      }
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

      while (column < gp.MAX_WORLD_COLUMNS && row < gp.MAX_WORLD_ROWS) {
        String line = br.readLine();

        while (column < gp.MAX_WORLD_COLUMNS) {
          String numbers[] = line.split("-");

          // int num = Integer.parseInt(numbers[column]);
          int num;

          try {
            num = Integer.parseInt(numbers[column]);
          } catch (Exception e) {
            num = 0;
          }

          mapTileNum[column][row] = num;
          column++;
        }

        if (column == gp.MAX_WORLD_COLUMNS) {
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

    int worldColumn = 0;
    int worldRow = 0;

    while (worldColumn < gp.MAX_WORLD_COLUMNS && worldRow < gp.MAX_WORLD_ROWS) {

      int tileNum = mapTileNum[worldColumn][worldRow];

      int worldX = worldColumn * gp.TILE_SIZE;
      int worldY = worldRow * gp.TILE_SIZE;
      int screenX = worldX - (gp.player.worldX + gp.player.SCREEN_X);
      int screenY = worldY - (gp.player.worldY + gp.player.SCREEN_Y);

      boolean isVisibleOnScreenX = (worldX > (gp.player.worldX - gp.player.SCREEN_X))
          && (worldX < (gp.player.worldX + gp.player.SCREEN_X));

      boolean isVisibleOnScreenY = (worldY > (gp.player.worldY - gp.player.SCREEN_Y))
          && (worldY < (gp.player.worldY + gp.player.SCREEN_Y));

      if (isVisibleOnScreenX && isVisibleOnScreenY) {
        g2d.drawImage(tiles[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
      }
      
      worldColumn++;

      if (worldColumn == gp.MAX_WORLD_COLUMNS) {
        worldColumn = 0;
        worldRow++;
      }
    }
  }
}
