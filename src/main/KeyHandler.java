package src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  public boolean upPressed, downPressed, leftPressed, rightPressed;
  public boolean interactPressed;
  public boolean isWalking = false;

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = true;
      isWalking = true;
    }

    if (code == KeyEvent.VK_A) {
      leftPressed = true;
      isWalking = true;
    }

    if (code == KeyEvent.VK_S) {
      downPressed = true;
      isWalking = true;
    }

    if (code == KeyEvent.VK_D) {
      rightPressed = true;
      isWalking = true;
    }

    if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
      interactPressed = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }

    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }

    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }

    if (code == KeyEvent.VK_D) {
      rightPressed = false;
    }

    if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
      interactPressed = false;
    }

    isWalking = upPressed || downPressed || leftPressed || rightPressed;
  }

}
