import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class Ball implements KeyListener {

    public int x, y;
    private int radius;
    private int stepSize;

    public Ball(int x, int y, int radius, int stepSize) {
        this.x = x;
        this.y = y;
        this.stepSize = stepSize;
        this.radius = radius;
    }

    public void draw() {
        DrawingBoard.bufferedG.setColor(Color.PINK);
        DrawingBoard.bufferedG.fillOval(x, y, 2 * radius, 2 * radius);
    }

    public void move() {
        y -= stepSize;
    }

    public void moveBack() {
        y += stepSize;
    }
    
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            Main.ball_y -= 400;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}