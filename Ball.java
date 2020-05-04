import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ball implements KeyListener {

    public int x, y;
    private int radius;
    private int stepSize;
    public static boolean isCollided;
    public Image explosion_large = new ImageIcon("explosion.png").getImage();
    public Image gameOver = new ImageIcon("gameOver2.png").getImage();

    public Ball(int x, int y, int radius, int stepSize) {
        this.x = x;
        this.y = y;
        this.stepSize = stepSize;
        this.radius = radius;
    }

    public void draw() {
        DrawingBoard.bufferedG.setColor(Color.CYAN);
        DrawingBoard.bufferedG.fillOval(x, y, 2 * radius, 2 * radius);
    }

    public void move() {
        y -= stepSize;
    }

    public void moveBack() {
        y += stepSize;
    }

    public void isHit() {
        if (isCollided == true) {
            DrawingBoard.bufferedG.drawImage(explosion_large, x-radius, y-radius, null);
            DrawingBoard.bufferedG.drawImage(gameOver, DrawingBoard.w/3, DrawingBoard.h/3, null);
        }
    }
    
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            Main.ballRun();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}