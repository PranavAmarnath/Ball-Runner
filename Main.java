import java.awt.*;

public class Main {

    public static boolean gameOn = true;
    public static int score;
    private static final int w = 1728;
    private static final int h = 972;
    public static int ball_x = 50;
    public static int ball_y = (h - 150);
    public static int obstacle_width = 50;
    public static int obstacle_height = 100;
    public static DrawingBoard board;
    public static Ball ball;
    public static Obstacle obstacle;

    public static void main(String[] args) {
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 20, 5);
        obstacle = new Obstacle(w - obstacle_width, h - obstacle_height, obstacle_width, obstacle_height, Color.BLACK);
        board.getJFrame().addKeyListener(ball);
        while(true) {
            board.clear();
            ball.draw();
            board.repaint();
            while (ball.ballOn) {
                ball.ballRun(board);
                ball.ballOn = false;
            }
//        ball.ballRun(board);
//        ball.ballRun(board);
//        ball.isCollided = true;
            ball.isHit();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
        }
    }

}