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
    private static File bgSound;
    private static File boing;

    public static void main(String[] args) {
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 15, 200);
        sound = new SoundPlayer();
        bgSound = new File("jack_low.wav");
        boing = new File("boing.wav");
        obstacle = new Obstacle(w - obstacle_width, h - obstacle_height, obstacle_width, obstacle_height, Color.BLACK);
        board.getJFrame().addKeyListener(ball);
        //SoundPlayer.playSound(bgSound);
        while(gameOn) {
            board.clear();
            ball.draw();
            board.repaint();
            while (ball.ballOn) {
                sound.playSound(boing);
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