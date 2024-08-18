package src.entity;

public enum Direction {
  UP("up"),
  DOWN("down"),
  LEFT("left"),
  RIGHT("right");

  private String direction;

  Direction(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return direction;
  }
}
