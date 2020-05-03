import java.awt.Graphics;

public class Main {

    public static boolean gameOn = true;
    public static int score;
    public static void main(String[] args){
        DrawingBoard board = new DrawingBoard(1920, 1000);
        Graphics g = board.getCanvas();
        Goat goat = new Goat(-20, 700, 100);
        board.getJFrame().addKeyListener(goat);
        while(true) {
            goat.draw(g);
            board.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            board.clear();
        }
    }
}