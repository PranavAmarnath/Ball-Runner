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
    public static int obstacle_width = 50;
    public static int obstacle_height = 100;
    public static DrawingBoard board;
    public static Ball ball;
    public static Obstacle obstacle;
    private static SoundPlayer sound;
    //private static File boing;

    public static void main(String[] args) {
        Thread obstacleThread = new Thread(obstacles);
        obstacleThread.start();
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 15, 200);
        sound = new SoundPlayer();
        Thread t = new Thread(sound);
        t.start();
        //boing = new File("boing.wav");
        //obstacle = new Obstacle(800, 350, obstacle_width, obstacle_height);
        board.getJFrame().addKeyListener(ball);
        while(gameOn) {
            board.clear();
            ball.draw();
            obstacle.drawOb(DrawingBoard.getCanvas());;
            board.repaint();
            while (ball.ballOn) {
                //sound.playSound(boing);
                ball.ballRun(board);
                ball.ballOn = false;
            }
            //ball.isCollided = true;
            //ball.isHit();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
        }
    }

}