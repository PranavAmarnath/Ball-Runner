public class Main {

    public static boolean gameOn = true;
    public static int score;
    private static final int w = 1728;
    private static final int h = 972;
    public static int ball_x = 50;
    public static int ball_y = (h - 150);
    private static int steps = 40;
    public static DrawingBoard board;
    public static Ball ball;

    // Loading Problem at beginning -> have to fix
    // Need to synchronize spacebar with ballRun();

    public static void main(String[] args) {
        board = new DrawingBoard(w, h);
        ball = new Ball(ball_x, ball_y, 20, 5);
        //board.clear();
        ballRun();
        ballRun();
        ball.isCollided = true;
        ball.isHit();
    }

    public static void ballRun() {
        board.clear();
        ball.draw();
        for (int i = 0; i < steps; i++) {
            ball.move();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
            }
            board.clear();
            ball.draw();
        }
        board.repaint();
        for (int i = 0; i < steps; i++) {
            ball.moveBack();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
            }
            board.clear();
            ball.draw();
        }
        board.repaint();
    }

}