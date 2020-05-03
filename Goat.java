import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Goat implements KeyListener {

    private static int size;

    private int x, y;
	private int stepSize;
    private Image goat = new ImageIcon("goatresized.png").getImage();

    public Goat(int x, int y, int stepSize) {
        this.x = x;
        this.y = y;
        this.stepSize = stepSize;
    }

    public void draw(Graphics g) {
        g.drawImage(goat, x - size/2, y - size/2, null);
    }

    public void move() {
        for(int i = 0; i < 4; i++){
            y -= (stepSize);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void moveBack() {
        for (int i = 0; i < 4; i++) {
            y += (stepSize);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    public void run() {
        move();
        moveBack();
    }
    
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if ((keyCode == KeyEvent.VK_UP) || (keyCode == KeyEvent.VK_SPACE)) {
            run();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}