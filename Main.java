import java.awt.*;
import java.io.File;

public class Main {

    // NOTE: THIS CLASS USES JDK 14. WHEN RUNNING, USE JDK 14. DO NOT USE JDK 9 OR PREVIOUS VERSIONS OF JAVA TO JDK 9.

    public static boolean gameOn = true;
    public static int score;
    private static final int w = 1024;
    private static final int h = 576;
    public static int ball_x = 50;
    public static int ball_y = (h - 150);
    public static int obstacle_height = 100;
    public static int obstacle_width = obstacle_height/2;
    public static DrawingBoard board;
    public static Ball ball;
    public static Obstacle obstacle;
    //public static ObstacleManager manager;
    private static SoundPlayer sound;
    //private static File boing;

    public static void main(String[] args) {
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 15, 200);
        sound = new SoundPlayer();
        Thread t = new Thread(sound);
        t.start();
        //boing = new File("boing.wav");
        obstacle = new Obstacle(800, 360, obstacle_width, obstacle_height);
        board.getJFrame().addKeyListener(ball);
        while(gameOn) {
            board.clear();
            ball.draw();
            obstacle.drawOb();
            obstacle.move();
            board.repaint();
            if(ball.ballOn) {
                //sound.playSound(boing);
                board.clear();
                ball.draw();
                obstacle.drawOb();
                obstacle.move();
                ball.move();
                board.clear();
                ball.draw();
                obstacle.drawOb();
                obstacle.move();
                board.repaint();
                for(int i = 0; i < 15; i++) {
                    obstacle.drawOb();
                    obstacle.move();
                    ball.draw();
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) { }
                    board.clear();
                    board.repaint();
                }
                ball.moveBack();
                board.clear();
                ball.draw();
                obstacle.drawOb();
                obstacle.move();
                board.repaint();
                ball.ballOn = false;
            }
            ball.isHit();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) { }
        }
    }

}