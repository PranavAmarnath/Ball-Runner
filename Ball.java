import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ball implements KeyListener /* Referenced code from https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html to learn about KeyListener */ {

    /* Key Concept: Object-Oriented Programming
       Used to create different objects such as the Ball object.
       Balls in our game have similar characteristics such as radius and stepSize.
    */
    private int x, y;
    private int radius;
    private int stepSize;
    public boolean isCollided;
    //private int steps = 40;
    public Image explosion_large = new ImageIcon("explosion_nontransparent.png").getImage();
    public Image gameOver = new ImageIcon("gameOver2.png").getImage();
    public static boolean ballOn = false;

    public Ball(int x, int y, int radius, int stepSize) {
        /*
           Domain: x, y, radius, stepSize of type integer
           Range: no return type but allows for initialization of Ball object
           Explanation: The purpose of this method is to set constructor variables to the instance variables.
        */
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.stepSize = stepSize;
    }

    public void draw() {
        DrawingBoard.getCanvas().setColor(Color.BLUE);
        DrawingBoard.getCanvas().fillOval(x, y, 2 * radius, 2 * radius);
    }

    public void move() {
        y -= stepSize;
    }

    public void moveBack() {
        y += stepSize;
    }

    public void isHit() {
        int center_x = x + radius;
        int center_y = y + radius;
        if(center_x >= Main.obstacles[Main.getIndex()].getX()-radius
                && center_y >= Main.obstacles[Main.getIndex()].getY()-radius
                && center_x <= Main.obstacles[Main.getIndex()].getX()+Main.obstacles[Main.getIndex()].getWidth()+radius) {
            isCollided = true;
        }
        if (isCollided) {
            Main.gameOn = false;
            DrawingBoard.getCanvas().drawImage(explosion_large, x-2*radius, y-2*radius, null);
            DrawingBoard.getCanvas().drawImage(gameOver, DrawingBoard.w/4, DrawingBoard.h/4, null);
        }
    }

    public void keyPressed(KeyEvent e) {
        /*
           User Input: The user can either press the up arrow key or the space bar key.
           How it's used: The commands are used to cause the ball to jump to avoid the obstacles.
        */
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP) {
            //System.out.println("entered to act on space bar");
            ballOn = true;
            //System.out.println("finished acting on space bar");
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}